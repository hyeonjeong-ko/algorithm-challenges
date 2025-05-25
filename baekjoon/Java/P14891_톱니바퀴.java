// simul
// medium

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14891_톱니바퀴 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1) 입력 및 초기화
        int[][] gears = new int[4][8];
        int[] head   = new int[4];    // 모두 0으로 시작 (12시 방향은 idx 0)
        for (int i = 0; i < 4; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = s.charAt(j) - '0';
            }
        }
        int K = Integer.parseInt(br.readLine().trim());

        // 2) 회전 명령 처리
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int d   = Integer.parseInt(st.nextToken());    // 1=시계, -1=반시계

            int[] dirs = new int[4];
            dirs[idx] = d;

            // 왼쪽 전파
            for (int i = idx; i > 0; i--) {
                int rightPole = gears[i][ (head[i] + 6) % 8 ];   // i의 9시
                int  leftPole = gears[i-1][ (head[i-1] + 2) % 8 ]; // i-1의 3시
                if (rightPole != leftPole) dirs[i-1] = -dirs[i];
                else break; // 전파중
            }
            // 오른쪽 전파
            for (int i = idx; i < 3; i++) {
                int leftPole  = gears[i][   (head[i] + 2) % 8  ]; // i의 3시
                int rightPole = gears[i+1][ (head[i+1] + 6) % 8 ]; // i+1의 9시
                if (leftPole != rightPole) dirs[i+1] = -dirs[i];
                else break;
            }

            // head만 조정해서 회전 효과 내기
            for (int i = 0; i < 4; i++) {
                if (dirs[i] == 1) {           // 시계
                    head[i] = (head[i] + 7) % 8;
                } else if (dirs[i] == -1) {   // 반시계
                    head[i] = (head[i] + 1) % 8;
                }
            }
        }

        // 3) 최종 점수 계산
        int score = 0;
        for (int i = 0; i < 4; i++) {
            // 12시 방향은 gears[i][ head[i] ]
            if (gears[i][ head[i] ] == 1) {
                score += (1 << i);
            }
        }
        System.out.println(score);
    }
}
