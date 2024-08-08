/*
 * DP
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] D = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                D[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 세가지 경우 고려 + 현재방의 사탕 개수 더하기!
                dp[i][j] = D[i][j] + Math.max(dp[i-1][j],Math.max(dp[i-1][j-1],dp[i][j-1]));
            }
        }
        System.out.println(dp[N][M]);
        
    }
}
