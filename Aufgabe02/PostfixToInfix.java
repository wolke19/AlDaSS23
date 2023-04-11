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

                case "+" -> {
                    double result = postfixStack.pop() + postfixStack.pop();
                    postfixStack.push(result);
                }

                case "-" -> {
                    double firstPop = postfixStack.pop();
                    double secondPop = postfixStack.pop();
                    double result = secondPop - firstPop;
                    postfixStack.push(result);
                }

                case "/" -> {
                    double nenner = postfixStack.pop();
                    double zaehler = postfixStack.pop();
                    double result = zaehler / nenner;
                    postfixStack.push(result);
                }

                case "*" -> {
                    double result = postfixStack.pop() * postfixStack.pop();
                    postfixStack.push(result);
                }

                default -> postfixStack.push(Double.parseDouble(temp));
            }
        }
        System.out.println("Result: " + postfixStack.pop());
        fileScanner.close();
    }
}
