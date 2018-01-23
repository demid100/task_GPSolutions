package task278;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        String father = reader.nextLine();
        String children = reader.nextLine();
        char[] fatherArray = father.toCharArray();
        char[] childrenArray = children.toCharArray();
        int j = 0;
        int fatherLenght = fatherArray.length - 1;
        int childrenLenght = childrenArray.length;

        for (int i = 0; i < childrenLenght; i++) {
            if (fatherArray[j] == childrenArray[i]) {
                j++;
                if (j > fatherLenght) break;

            }
        }
        if (j == fatherLenght+1) writer.print("YES");
        else writer.print("NO");

        reader.close();
        writer.close();

    }
}
