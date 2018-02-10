package phasetwo.filtertext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> inputList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;

            while (!(input = bufferedReader.readLine()).equals("")) {
                inputList.add(input);
            }
        }

        FilterText filterText = new FilterText(args, inputList);
        filterText.filter();
    }
}
