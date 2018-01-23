package task557;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String[] stringArray = reader.readLine().split(" ");
        int quantityMatric = Integer.parseInt(stringArray[0]);
        int sizeMatric = Integer.parseInt(stringArray[1]);
        stringArray = reader.readLine().split(" ");
        int foundI =  Integer.parseInt(stringArray[0]);
        int foundJ =  Integer.parseInt(stringArray[1]);
        int digitP = Integer.parseInt(reader.readLine());

        int[][][] superMatrica = new int[quantityMatric][sizeMatric][sizeMatric];
        reader.readLine();
        String[] elementArray;
        for (int k = 0; k < quantityMatric; k++) {
            for (int i = 0; i < sizeMatric; i++) {
                elementArray = reader.readLine().split(" ");
                 for (int j = 0; j < sizeMatric; j++) {
                     superMatrica[k][i][j] = Integer.parseInt(elementArray[j]);
                }
            }
            reader.readLine();
        }
        int[] temp = new int[sizeMatric];
        int result[] = superMatrica[0][foundI-1];

        for (int m = 1; m < quantityMatric ; m++) {

            System.arraycopy(result, 0, temp, 0, sizeMatric);

             result = new int[sizeMatric];

                for (int j = 0; j < sizeMatric; j++) {

                    int t;
                    for (int k = 0; k < sizeMatric; k++) {
                        if ((t= temp[k]*superMatrica[m][k][j] %digitP) >= digitP) t %= digitP;
                        if ((result[j] = result[j] + t) >= digitP ) result[j] %= digitP;

                    }
                }
            }

        reader.close();
        writer.print(result[foundJ-1]);
        writer.flush();
        writer.close();

    }
}
