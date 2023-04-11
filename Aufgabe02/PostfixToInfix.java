import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class PostfixToInfix {
    public static void main(String[] args) throws IOException {
        Stack<Double> postfixStack = new Stack<Double>();
        Path inputPath = Paths.get("Aufgabe02/PostfixInput.txt");

        Scanner fileScanner = new Scanner(inputPath);
        while (fileScanner.hasNext()){
            String temp = fileScanner.next();

            switch (temp){

                case "+" -> postfixStack.push(postfixStack.pop() + postfixStack.pop()) ;
                case "*" -> postfixStack.push(postfixStack.pop() * postfixStack.pop());

                case "-" -> {
                    double firstPop = postfixStack.pop();
                    double secondPop = postfixStack.pop();
                    postfixStack.push(secondPop-firstPop);
                }

                case "/" -> {
                    double nenner = postfixStack.pop();
                    double zaehler = postfixStack.pop();
                    postfixStack.push(zaehler / nenner);
                }

                default -> postfixStack.push(Double.parseDouble(temp));
            }
        }
        System.out.println("Result: " + postfixStack.pop());
        fileScanner.close();
    }
}
