package MSCampus;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Feiyu on 2015/7/12.
 **/
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt(), b = in.nextInt();
        int D = Math.max(a, b);
        int X = Math.min(a, b);
        ArrayList<Integer> vx = new ArrayList<>(),
                vd = new ArrayList<>();
        for (int i = 1; i <= X; i++) {
            if (X % i == 0) {
                vx.add(i);
            }
        }
        for (int i = 1; i <= D; i++) {
            if (D % i == 0) {
                vd.add(i);
            }
        }
        for (int i = 0; i < vd.size(); i++) {
            for (int j = 0; j < vx.size(); j++) {
                System.out.println(vd.get(i) + " " + vx.get(j));
            }
        }
    }
}

