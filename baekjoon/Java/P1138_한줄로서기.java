import java.util.Scanner;

public class P1138_한줄로서기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        int[] ans = new int[N]; // 0은 빈자리취급

        for (int i = 0; i < N; i++) {
            int front = A[i];
            int empty = 0;

            for (int j = 0; j < N; j++) {
                if (ans[j] == 0) {
                    empty++;
                    // 빈칸을 front+1개 만난 순간이 이 사람자리!
                    if (empty > front) {
                        ans[j] = i + 1;
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int x : ans) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());

    }
}
