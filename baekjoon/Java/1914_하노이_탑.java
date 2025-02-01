import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static StringBuilder sb = new StringBuilder();
    
    // 하노이 탑 재귀 함수
    // n개의 원판을 start에서 end로 옮길 때, via를 보조 기둥으로 사용
    static void hanoi(int n, int start, int end, int via) {
        if (n == 1) {
            // 원판 1개를 옮기는 경우
            sb.append(start).append(" ").append(end).append("\n");
        } else {
            // 1) n-1개를 start -> via (end를 보조로)
            hanoi(n - 1, start, via, end);
            
            // 2) 가장 큰 원판(n번째)을 start -> end
            sb.append(start).append(" ").append(end).append("\n");
            
            // 3) n-1개를 via -> end (start를 보조로)
            hanoi(n - 1, via, end, start);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        // 최소 이동 횟수 K = 2^N - 1
        BigInteger K = BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE);
        
        // 1) 이동 횟수 출력
        System.out.println(K);
        
        // 2) N이 20 이하일 때만 실제 이동 과정을 출력
        if (N <= 20) {
            hanoi(N, 1, 3, 2);
            System.out.print(sb);
        }
    }
}
