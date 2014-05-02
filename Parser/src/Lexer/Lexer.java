package Lexer;

import Lexer.LexemeParsers.*;
import Lexer.LexemeParsers.SimpleLexemeParser;
import Lexer.Token.Token;
import Lexer.Token.TokenType;

import java.util.ArrayList;

/**
 * Created by Admin on 4/27/14.
 */
public class Lexer implements ILexer {
    ArrayList<ILexemeParser> lexemeParsers;
    String input;
    int positionInInput;

    public Lexer() {
        initializeLexemeParsers();
        input = "";
        positionInInput = 0;
    }

    public void setInput(String input){
        this.input = input;
        positionInInput = 0;
    }

    private void initializeLexemeParsers() {
        lexemeParsers = new ArrayList<ILexemeParser>();

        lexemeParsers.add(new SimpleLexemeParser(TokenType.PLUS, "+"));
        lexemeParsers.add(new SimpleLexemeParser(TokenType.MINUS, "-"));
        lexemeParsers.add(new SimpleLexemeParser(TokenType.MUL, "*"));
        lexemeParsers.add(new SimpleLexemeParser(TokenType.DIV, "/"));
        lexemeParsers.add(new SimpleLexemeParser(TokenType.ASSIGN, "="));
        lexemeParsers.add(new SimpleLexemeParser(TokenType.LEFT_BRACKET, "("));
        lexemeParsers.add(new SimpleLexemeParser(TokenType.RIGHT_BRACKET, ")"));
        lexemeParsers.add(new NumLexemeParser());
        lexemeParsers.add(new VarLexemeParser());
        lexemeParsers.add(new ErrorLexemeParser());
    }

    public Token getNextToken() {
        trimWhitespace();
        for (ILexemeParser lexemeParser : lexemeParsers) {
            Token token;
            if (positionInInput != input.length()
                    &&(token = lexemeParser.parse(input, positionInInput)) != null) {
                positionInInput += token.getLength();
                return token;
            }
        }
        return new Token(TokenType.NOTHING, "", positionInInput);
    }

    private void trimWhitespace() {
        while (positionInInput != input.length() && Character.isWhitespace(input.charAt(positionInInput))) {
            positionInInput++;
        }
    }
}
