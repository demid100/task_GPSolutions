package task670;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int numberIndex = scanner.nextInt();
        int count = 0;
        int digit = 0;
        boolean flag;
        while (count!= numberIndex){
            digit++;
            flag = true;
            char[] digitArr = Integer.toString(digit).toCharArray();

                for (int i = 0; i < digitArr.length; i++) {
                    for (int j = 0; j < digitArr.length; j++) {
                        if (i != j && digitArr[i] == digitArr[j]) flag = false;
                    }
                }
                if (flag) count++;
            }
        scanner.close();
        writer.print(digit);
        writer.flush();
        writer.close();

    }
}
