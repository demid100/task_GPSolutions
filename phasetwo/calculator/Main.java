package phasetwo.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
        public static void main(String[] args) throws IOException {
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                        String inputString = bufferedReader.readLine();

                        Calculator calculator = new Calculator(inputString);
                        calculator.validExpression();
                        System.out.println(calculator.openBranchesAndCalculate());

            }catch (InvalidException e) {
                System.out.println(e.getMessage());
            }
        }

}
