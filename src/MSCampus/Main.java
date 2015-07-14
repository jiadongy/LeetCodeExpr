package MSCampus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Feiyu on 2015/7/12.
 **/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int number = a;
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            while (number > 1 && number != i) {
                if (number % i == 0) {
                    list.add(i);
                    number = number / i;
                } else {
                    break;
                }
            }
        }
        Collections.reverse(list);
        int[] solution = new int[list.size()];
        for (int i = 0; i < solution.length; i++)
            solution[i] = -1;
        int index = 0;
        while (index >= 0) {
            solution[index]++;
            while (getNumber(list, solution) <= number)
                solution[index]++;
            if (solution[index] <= Math.sqrt(number)) {
                if (getNumber(list, solution) == number) {
                    break;
                } else {
                    index++;
                    solution[index]++;
                }
            } else
                index--;
        }

        for (int i = 0; i < solution.length; i++) {
            if (solution[i] > 0) {
                solution[i]--;
            }
            for (int j = 0; j < list.get(i); j++) {

            }
        }


    }

    private static int getNumber(List<Integer> list, int[] solution) {
        int sum = 0;
        for (int i = 0; i < solution.length; i++)
            sum += Math.pow(list.get(0), solution[i]);
        return sum;
    }


}
