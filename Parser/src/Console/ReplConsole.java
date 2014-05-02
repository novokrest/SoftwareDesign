package Console;

import Exp.ErrorExp;
import Exp.Exp;
import ExpVisitor.Evaluator.Evaluator;
import ExpVisitor.Printer;
import Lexer.Lexer;
import Lexer.Token.Token;
import Lexer.Token.TokenType;
import Lexer.TokenIterator.ITokenIterator;
import Lexer.TokenIterator.TokenIterator;
import Parser.Parser;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 5/2/14.
 */

public class ReplConsole {
    private Lexer lexer;
    private Parser parser;
    Map<String, Exp> context;
    private Evaluator evaluator;
    private Printer printer;

    private final static String SIMPLIFY = "SimplifyMode";
    private final static String EVALUATE = "EvaluateMode";
    private boolean isSimplifyMode = true;
    private final static String GREETING = System.lineSeparator() + ">";

    UndoManagerUserInput undoManagerUserInput;
    UndoManagerExpression undoManagerExpression;

    public ReplConsole() {
        lexer = new Lexer();
        parser = new Parser();
        context = new HashMap<String, Exp>();
        evaluator = new Evaluator(context);
        printer = new Printer();

        try {
            initFrame();
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void initFrame() throws BadLocationException {
        JFrame mainFrame = new JFrame();
        JComboBox<String> optionBox = initComboBox();
        JTextPane textPane = initPane();

        mainFrame.add(optionBox, "North");
        mainFrame.add(new JScrollPane(textPane), "Center");
        mainFrame.setSize(500, 300);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private JComboBox<String> initComboBox() {
        JComboBox<String> optionBox = new JComboBox<String>();
        optionBox.addItem(SIMPLIFY);
        optionBox.addItem(EVALUATE);
        optionBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> source = (JComboBox<String>) e.getSource();
                if (SIMPLIFY.equals(source.getSelectedItem())) {
                    evaluator.setSimplifyMode();
                    isSimplifyMode = true;
                } else {
                    evaluator.setEvaluateMode();
                    isSimplifyMode = false;
                }
            }
        });
        return optionBox;
    }

    private JTextPane initPane() throws BadLocationException {
        JTextPane textPane = new JTextPane();
        AbstractDocument document = (AbstractDocument) textPane.getDocument();
        document.setDocumentFilter(new Filter());
        undoManagerUserInput = new UndoManagerUserInput(document);
        undoManagerExpression = new UndoManagerExpression(context);

        textPane.getKeymap().addActionForKeyStroke(KeyStroke.getKeyStroke("ENTER"),
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JTextPane source = (JTextPane) e.getSource();
                        StyledDocument document = source.getStyledDocument();
                        try {
                            String lastUserInput = getLastUserInput(document);
                            lexer.setInput(lastUserInput);
                            Exp parsedExp = parser.parse(new TokenIterator(lexer));
                            undoManagerExpression.addEdit(parsedExp);
                            Exp evaluatedExp = parsedExp.accept(evaluator);
                            String result = evaluatedExp.accept(printer);
                            document.insertString(endOffset(document), System.lineSeparator() + result + GREETING, null);
                        } catch (BadLocationException ex) {

                        }
                    }
                });

        textPane.getKeymap().addActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK),
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Ctrl+Z pressed");
                    }
                }
        );

        textPane.getActionMap().put("UndoUserInput",
                new AbstractAction("") {
                    public void actionPerformed(ActionEvent e) {
                        if (undoManagerUserInput.canUndo()) {
                            try {
                                undoManagerUserInput.undo();
                            } catch (BadLocationException ex) {
                            }
                        }
                    }
                });

        textPane.getActionMap().put("UndoExpression",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (undoManagerExpression.canUndo()) {
                            undoManagerExpression.undo();
                        }
                    }
                });

        textPane.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "UndoUserInput");
        textPane.getInputMap().put(KeyStroke.getKeyStroke("control shift Z"), "UndoExpression");

        textPane.getDocument().insertString(0, "Welcome to ReplConsole! " + GREETING, null);
        return textPane;
    }

    private class Filter extends DocumentFilter {
        private SimpleAttributeSet numAttrs;
        private SimpleAttributeSet varAttrs;
        private SimpleAttributeSet operatorAttrs;
        private SimpleAttributeSet errorAttrs;

        public Filter() {
            super();

            numAttrs = new SimpleAttributeSet();
            StyleConstants.setForeground(numAttrs, Color.green);

            varAttrs = new SimpleAttributeSet();
            StyleConstants.setForeground(varAttrs, Color.blue);
            StyleConstants.setItalic(varAttrs, true);

            operatorAttrs = new SimpleAttributeSet();
            StyleConstants.setForeground(operatorAttrs, Color.black);

            errorAttrs = new SimpleAttributeSet();
            StyleConstants.setForeground(errorAttrs, Color.red);
            StyleConstants.setUnderline(errorAttrs, true);
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isCursorAfterLastGreeting(fb.getDocument(), offset)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
            if (isCursorAfterLastGreeting(fb.getDocument(), offset)) {
                super.remove(fb, offset, length);
                refresh(fb);
            }
        }

        @Override
        public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs)
                throws BadLocationException {
            if (isCursorAfterLastGreeting(fb.getDocument(), offset)) {
                super.replace(fb, offset, length, text, attrs);
                undoManagerUserInput.addEdit(offset, text);
                refresh(fb);
            }
        }

        private void refresh(FilterBypass fb) throws BadLocationException {
            StyledDocument document = (StyledDocument) fb.getDocument();
            String userInput = getLastUserInput(fb.getDocument());
            int startOffset = getAfterLastGreetingIndex(fb.getDocument());

            lexer.setInput(userInput);
            ITokenIterator tokenIterator = new TokenIterator(lexer);
            tokenIterator.next();

            while (tokenIterator.get().getType() != TokenType.NOTHING) {
                Token current = tokenIterator.get();
                document.setCharacterAttributes(startOffset + current.getStartPosition(),
                        startOffset + current.getStartPosition() + current.getLength(),
                        getAttributeSet(current),
                        true);
                tokenIterator.next();
            }

            tokenIterator.begin();
            Exp parsedExp = parser.parse(tokenIterator);
            if (parsedExp instanceof ErrorExp) {
                document.setCharacterAttributes(startOffset,
                        startOffset + userInput.length(),
                        errorAttrs,
                        true);
            }
        }

        private AttributeSet getAttributeSet(Token token) {
            if (token.isOperator()) {
                return operatorAttrs;
            }
            if (token.getType() == TokenType.NUM) {
                return numAttrs;
            }
            if (token.getType() == TokenType.VAR) {
                if (isSimplifyMode || context.containsKey(token.getValue())) {
                    return varAttrs;
                }
            }
            return errorAttrs;
        }
    }

    private String getLastUserInput(Document document) throws BadLocationException {
        int afterLastGreetingIndex = getAfterLastGreetingIndex(document);
        return document.getText(afterLastGreetingIndex, document.getLength() - afterLastGreetingIndex);
    }

    private int getAfterLastGreetingIndex(Document document) throws BadLocationException {
        int lastGreetingIndex = document.getText(0, document.getLength()).lastIndexOf(GREETING);
        return lastGreetingIndex < 0 ? 0 : lastGreetingIndex + GREETING.length();
    }

    private boolean isCursorAfterLastGreeting(Document document, int offset) throws BadLocationException {
        return offset >= getAfterLastGreetingIndex(document);
    }

    private int endOffset(Document document) {
        return document.getEndPosition().getOffset() - 1;
    }
}
