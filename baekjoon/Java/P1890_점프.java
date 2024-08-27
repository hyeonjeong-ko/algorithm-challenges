/*
algorithm: dp
주의점: -2^63 ~ 2^63+1 범위 내에서는 long을 사용함에 주의!
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        long[][] dp = new long[N][N]; //dp 배열 초기화
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) { //배열 입력받기
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(dp[i][j] > 0 && arr[i][j] > 0) {
                    int jump = arr[i][j];
                    if (i + jump < N) {
                        dp[i + jump][j] += dp[i][j];
                    }
                    if (j + jump < N) {
                        dp[i][j + jump] += dp[i][j];
                    }
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }
}
