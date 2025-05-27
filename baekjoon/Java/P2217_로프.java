// 간단수학, simul

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P2217_로프 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Integer[] ropes = new Integer[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = sc.nextInt();
        }

        Arrays.sort(ropes, Collections.reverseOrder());

        long maxWeight = 0;
        for (int k = 0; k < N; k++) {
            long weight = ropes[k] * (k + 1);
            if (weight > maxWeight)
                maxWeight = weight;

        }
        System.out.println(maxWeight);
    }
}
