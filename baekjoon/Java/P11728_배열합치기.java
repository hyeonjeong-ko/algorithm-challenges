// algo: two pointer
// level: easy

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11728_배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];

        // 배열 A 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 B 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;

        while (i < N && j < M) {
            if (A[i] < B[j]) {
                sb.append(A[i]).append(" ");
                i++;
            } else {
                sb.append(B[j]).append(" ");
                j++;
            }
        }

        // 남은 값 추가
        while (i < N) sb.append(A[i++]).append(" ");
        while (j < M) sb.append(B[j++]).append(" ");

        System.out.println(sb);
    }
}
