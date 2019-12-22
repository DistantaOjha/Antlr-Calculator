import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        CalculatorParser parser = new CalculatorParser(null);
        CalculatorBaseVisitorImpl calcVisitor = new CalculatorBaseVisitorImpl();
        System.out.println();
        System.out.println("Starting Calculator...");
        System.out.println("Enter '<exit>' to exit the Calculator");
        System.out.println("_____________________________________");
        System.out.println();
        while(true){
            String line = scan.nextLine().trim();
            if(line.equals("<exit>")){
                break;
            }
            else if(line!=""){
                CharStream input = CharStreams.fromString(line);
                CalculatorLexer lexer = new CalculatorLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                parser.setTokenStream(tokens);
                ParseTree tree = parser.input();
                calcVisitor.visit(tree);
            }
        }
        System.out.println();
        System.out.println("Thank you for using this Calculator...");
        System.out.println("---------------------------------------");
        System.out.println();
    }
}
