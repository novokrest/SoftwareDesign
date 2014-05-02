package Console;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Stack;

/**
 * Created by Admin on 5/2/14.
 */
public class UndoManagerUserInput {
    private final Document document;
    private Stack<UserInput> userInputs;

    public UndoManagerUserInput(Document document) {
        this.document = document;
        userInputs = new Stack<UserInput>();
    }

    public void addEdit(int offset, String text) {
        userInputs.add(new UserInput(offset, text));
    }

    public boolean canUndo() {
        return userInputs.size() > 0;
    }

    public void undo() throws BadLocationException {
        UserInput lastUserInput = userInputs.pop();
        document.remove(lastUserInput.offset, lastUserInput.text.length());
    }

    private class UserInput {
        int offset;
        String text;

        public UserInput(int offset, String text) {
            this.offset = offset;
            this.text = text;
        }
    }
}
