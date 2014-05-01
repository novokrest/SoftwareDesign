package Lexer.TokenIterator;

import Lexer.Token.Token;

/**
 * Created by Admin on 4/27/14.
 */
public interface ITokenIterator {
    public Token get();
    public void next();
    public void prev();
}
