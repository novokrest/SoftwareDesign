package Lexer.TokenIterator;

import Lexer.*;
import Lexer.Token.Token;

import java.util.ArrayList;

/**
 * Created by Admin on 4/27/14.
 */
public class TokenIterator implements ITokenIterator {
    ILexer lexer;
    ArrayList<Token> tokens;
    int position;

    public TokenIterator(ILexer lexer) {
        this.lexer = lexer;
        tokens = new ArrayList<Token>();
        position = -1;
    }

    @Override
    public Token get() {
        return tokens.get(position);
    }

    @Override
    public void next() {
        position++;
        if (position == tokens.size()) {
            tokens.add(lexer.getNextToken());
        }
    }

    @Override
    public void prev() {
        if (position != 0) { position--; }
    }
}
