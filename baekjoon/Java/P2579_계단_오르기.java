/*
- 예외 처리: 계단의 수가 적은 경우(1개 또는 2개) DP 배열을 개별적으로 초기화
- 조건 반영한 점화식 설계: 세 계단을 연속해서 밟을 수 없다는 조건을 점화식에 반영
- 문제 접근 방식: 시작점부터 하나씩 확인하는 것보다 도착 지점을 기준으로 조건을 만족하는 최적의 경로를 찾는 사고의 전환이 필요
*/
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 계단 개수
        int[] stairs = new int[N + 1];
        int[] dp = new int[N + 1]; // 해당 계단까지의 최대 점수
        
        for (int i = 1; i <= N; i++) { // 계단 점수 입력받기
            stairs[i] = Integer.parseInt(br.readLine());
        }
        // 계단 개수 1개 또는 2개인 경우; 예외처리
        if (N == 1) {
            System.out.println(stairs[1]);
            return;
        } else if (N == 2) {
            System.out.println(stairs[1] + stairs[2]);
            return;
        }
        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];
        
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i-2]+stairs[i], dp[i-3]+stairs[i-1]+stairs[i]);
        }
        
        System.out.println(dp[N]);
    }
}
