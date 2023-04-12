import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class PostfixCalculator {
    public static void main(String[] args) throws IOException {
        Stack<Double> postfixStack = new Stack<Double>();
        Path inputPath = Paths.get("Aufgabe02/PostfixInput.txt");
        Scanner fileScanner = new Scanner(inputPath);

        while (fileScanner.hasNext()){
            String temp = fileScanner.next();

            switch (temp){
                case "+" -> postfixStack.push(postfixStack.pop() + postfixStack.pop());
                case "*" -> postfixStack.push(postfixStack.pop() * postfixStack.pop());
                case "-" -> postfixStack.push(- postfixStack.pop() + postfixStack.pop());
                case "/" -> postfixStack.push(Math.pow((postfixStack.pop() / postfixStack.pop()), -1));
                default  -> postfixStack.push(Double.parseDouble(temp));
            }
        }

        System.out.println("Result: " + postfixStack.pop());
        fileScanner.close();
    }
}
