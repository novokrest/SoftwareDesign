package Lexer.LexemeParsers;

import Lexer.Token.Token;
import Lexer.Token.TokenType;

/**
 * Created by Admin on 4/27/14.
 */
public class ErrorLexemeParser implements ILexemeParser {

    @Override
    public Token parse(String input, int startPosition) {
        StringBuilder errorLexeme = new StringBuilder();
        int currentPosition = startPosition;

        while (currentPosition != input.length()
                && !Character.isWhitespace(input.charAt(currentPosition))) {
            errorLexeme.append(input.charAt(currentPosition));
            currentPosition++;
        }

        return new Token(TokenType.ERROR, errorLexeme.toString(), startPosition);
    }
}
