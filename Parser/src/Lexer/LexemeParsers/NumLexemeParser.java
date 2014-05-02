package Lexer.LexemeParsers;

import Lexer.Token.Token;
import Lexer.Token.TokenType;

/**
 * Created by Admin on 4/27/14.
 */
public class NumLexemeParser implements ILexemeParser {

    @Override
    public Token parse(String input, int startPosition) {
        int currentPosition = startPosition;
        StringBuilder number = new StringBuilder();
        while (currentPosition != input.length()
                && Character.isDigit(input.charAt(currentPosition))) {
            number.append(input.charAt(currentPosition));
            currentPosition++;
        }
        if (number.length() == 0) {
            return null;
        }
        return new Token(TokenType.NUM, number.toString(), startPosition);
    }
}
