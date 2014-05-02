package Lexer.LexemeParsers;

import Lexer.Token.Token;

/**
 * Created by Admin on 4/27/14.
 */
public interface ILexemeParser {
    Token parse(String input, int startPosition);
}
