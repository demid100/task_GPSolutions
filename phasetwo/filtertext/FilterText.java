package phasetwo.filtertext;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterText {
    private String[] args;
    private List<String> inputList;

    public FilterText(String[] args, List<String> inputList) {
        this.args = args;
        this.inputList = inputList;
    }

    public void filter() {
        Pattern[] pattern = new Pattern[args.length];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i] = Pattern.compile(args[i]);
        }
        System.out.println("Вывод:");

        Iterator<String> iterator = inputList.iterator();
        label:
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] inputLineArray = line.split(" ");
            for (int i = 0; i < inputLineArray.length; i++) {
                // этот if для того, чтобы вывод был точно такой как в задании,
                // если строка входных данных будет заканчиваться на ";"
                if ((i == (inputLineArray.length - 1)) && inputLineArray[i].endsWith(";"))
                    inputLineArray[i] = inputLineArray[i].substring(0, inputLineArray[i].length() - 1);
                for (Pattern pattern1Current : pattern) {

                    Matcher matcher = pattern1Current.matcher(inputLineArray[i]);
                    if (matcher.matches()) {
                        System.out.println(line);
                        iterator.remove();
                        continue label;
                    }
                }
            }
        }

    }
}
