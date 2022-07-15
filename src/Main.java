import java.util.Scanner;
public class Main {
    static Calculator kataCalc = new Calculator();

    public static void main(String[] args) throws  Throwable{
        try {
            while (true) {
            System.out.print(">");
            System.out.println("Result: " + calc(new Scanner(System.in).nextLine()));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static String calc(String input) throws Throwable {
        return kataCalc.NextExpression(input);
    }
}