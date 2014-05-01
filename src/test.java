import Evaluator.PrettyPrinter;
import Exp.Exp;
import Lexer.Token.Token;
import Lexer.Token.TokenType;
import Lexer.Lexer;
import Lexer.TokenIterator.TokenIterator;
import Parser.Parser;

/**
 * Created by Admin on 4/27/14.
 */
public class test {
    public static void main(String[] args) {
        String input = "    123+x*(x - 2) + 3*(3+1)*4-9";
        System.out.println("Input: " + input);

        Lexer lexer = new Lexer();
        lexer.setInput(input);
        TokenIterator tokenIterator = new TokenIterator(lexer);
        Parser parser = new Parser(tokenIterator);
        Exp exp = parser.parse();
        String printExp = exp.accept(new PrettyPrinter());
        System.out.println(printExp);



        /*Token token;
        while ((token = tokenIterator.get()).getType() != TokenType.NOTHING) {
            System.out.println(token.getType() + " : " + token.getValue());
            tokenIterator.next();
        }*/
    }
}
