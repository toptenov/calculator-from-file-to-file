import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        FileReader fr = new FileReader("file.txt");
        Scanner sc = new Scanner(fr);
        String a, sign, b, result;
        while (sc.hasNextLine()) {
            a = sc.next();
            sign = sc.next();
            b = sc.nextLine().strip();
            try {
                result = Double.toString(calculate(a, sign, b));
                fw.write(a + " " + sign + " " + b + " = " + result + "\n");
            } catch (MyZeroException | MyOperationException | MyNotNumberException e) {
                fw.write(a + " " + sign + " " + b + " = " + e.getMessage() + "\n");
            }
        }
        fr.close();
        sc.close();
        fw.close();
    }

    public static double calculate(String a, String sign, String b) throws
            MyZeroException, MyOperationException, MyNotNumberException {
        double double_a;
        double double_b;
        try {
            double_a = Double.parseDouble(a);
            double_b = Double.parseDouble(b);
        }
        catch(Exception e) {
            throw new MyNotNumberException("Error! Not number");
        }
        if (sign.equals("+"))
            return double_a + double_b;
        if (sign.equals("-"))
            return double_a - double_b;
        if (sign.equals("*"))
            return double_a * double_b;
        if (sign.equals("/")) {
            if (double_b != 0)
                return double_a / double_b;
            else
                throw new MyZeroException("Error! Division by zero");
        } else
            throw new MyOperationException("Operation Error!");
    }

    private static class MyOperationException extends Exception {
        public MyOperationException(String errorMessage) { super(errorMessage); }
    }

    private static class MyZeroException extends Exception {
        public MyZeroException(String errorMessage) { super(errorMessage); }
    }

    private static class MyNotNumberException extends Exception {
        public MyNotNumberException(String errorMessage) { super(errorMessage); }
    }
}