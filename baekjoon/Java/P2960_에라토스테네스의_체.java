
import java.util.Scanner;

public class P_2960 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] A = new int[N + 1];
        for(int i = 2; i <=N; i++) A[i] = i; // 인덱스 값으로 초기화
        int cnt = 0;

        for(int i = 2; i <= N; i++) { // 소수 아닌 값 0 표시로 지워가기
            for (int j = i; j <= N; j = j + i) { // 배수 지우기
                if (A[j] == 0) continue; // 소수 X 넘어가기
                int tmp = A[j];
                A[j] = 0;
                cnt += 1;

                if (cnt == K) {
                    System.out.println(tmp);
                    return;
                }
            }
        }
    }
}
