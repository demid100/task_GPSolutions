package task579;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int quantiryDigit = scanner.nextInt();
        int[] digit = new int[quantiryDigit];
        for (int i = 0; i < quantiryDigit; i++) {
          if (scanner.hasNext()) digit[i] = scanner.nextInt();
        }

        List<Integer> indexPositiv = new ArrayList<>();
        List<Integer> indexNegativ = new ArrayList<>();
        int summaPositiv = 0;
        int summaNegativ = 0;

        for (int i = 0; i < digit.length ; i++) {
            if (digit[i] > 0){
                summaPositiv += digit[i];
                indexPositiv.add(i+1);
            }
            else {
                summaNegativ += digit[i];
                indexNegativ.add(i+1);
            }
        }

        if (summaPositiv > Math.abs(summaNegativ)){
            writer.println(indexPositiv.size());
            for (Integer index : indexPositiv) {
                writer.print(index + " ");
            }
        }else{
            writer.println(indexNegativ.size());
            for (Integer index : indexNegativ) {
                writer.print(index + " ");
            }
        }

        scanner.close();
        writer.close();

    }
}
