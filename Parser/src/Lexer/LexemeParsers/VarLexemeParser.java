package Lexer.LexemeParsers;

import Lexer.Token.Token;
import Lexer.Token.TokenType;

/**
 * Created by Admin on 4/27/14.
 */
public class VarLexemeParser implements ILexemeParser {

    @Override
    public Token parse(String input, int startPosition) {
        if (!Character.isLetter(input.charAt(startPosition))
                && !isUnderscore(input.charAt(startPosition))) {
            return null;
        }
        int currentPosition = startPosition;
        StringBuilder variable = new StringBuilder();
        while (currentPosition != input.length()
                && isLetterOrDigitOrUnderscore(input.charAt(currentPosition))) {
            variable.append(input.charAt(currentPosition));
            currentPosition++;
        }
        if (variable.length() == 0) {
            return null;
        }
        return new Token(TokenType.VAR, variable.toString(), startPosition);
    }

    private boolean isVariable(String lexeme) {
        if (!Character.isLetter(lexeme.charAt(0)) && !isUnderscore(lexeme.charAt(0))) {
            return false;
        }
        for (int i = 0; i < lexeme.length(); i++) {
            if (!isLetterOrDigitOrUnderscore(lexeme.charAt(i))) {return false; }
        }
        return true;
    }

    private boolean isLetterOrDigitOrUnderscore(char symbol) {
        if (Character.isLetterOrDigit(symbol) || isUnderscore(symbol)) {
            return true;
        }
        return false;
    }

    private boolean isUnderscore(char symbol) {
        if (symbol == '_') {
            return true;
        }
        return false;
    }
}
