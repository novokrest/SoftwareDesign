import ExpVisitor.Printer;
import Exp.Exp;
import Lexer.Lexer;
import Lexer.TokenIterator.TokenIterator;
import Parser.Parser;

/**
 * Created by Admin on 4/27/14.
 */
public class test {
    public static void main(String[] args) {
        String input = " y + 123+x*(x - 2) + 3*(3+1)*4-9";
        System.out.println("Input: " + input);

        Lexer lexer = new Lexer();
        lexer.setInput(input);
        TokenIterator tokenIterator = new TokenIterator(lexer);
        Parser parser = new Parser();
        Exp exp = parser.parse(tokenIterator);
        String printExp = exp.accept(new Printer());
        System.out.println(printExp);



        /*Token token;
        while ((token = tokenIterator.get()).getType() != TokenType.NOTHING) {
            System.out.println(token.getType() + " : " + token.getValue());
            tokenIterator.next();
        }*/
    }
}
