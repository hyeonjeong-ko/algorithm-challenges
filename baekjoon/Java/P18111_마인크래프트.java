/* Simul
* diff > 0 → 블록 제거: 시간 += 2초, 인벤토리 += 블록
* diff < 0 → 블록 추가: 시간 += 1초, 인벤토리 -= 블록
* inventory가 부족하면 해당 높이는 불가능 → continue
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111_마인크래프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int B = Integer.parseInt(st.nextToken()); // 인벤토리 블록 수

        int[] heightCnt = new int[257];

        // 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int h = Integer.parseInt(st.nextToken());
                heightCnt[h]++;
            }
        }

        int minTime = Integer.MAX_VALUE;
        int bestHeight = 0;
        for (int target = 0; target <= 256; target++) { // 목표 높이 0~256 모두 시도
            int time = 0;
            int inventory = B;

            for (int h = 0; h <= 256; h++) {
                int cnt = heightCnt[h];
                int diff = h - target;

                if (diff > 0) { // 블록 제거해야
                    time += diff * 2 * cnt;
                    inventory += diff * cnt;
                } else if (diff < 0) { // (-diff) 만큼 추가해야
                    time += (-diff) * cnt;
                    inventory -= (-diff) * cnt;
                }
            }

            if (inventory < 0) continue; // 인벤토리 부족 시 불가

            if (time <= minTime) {
                minTime = time;
                bestHeight = target;
            }
        }
        System.out.println(minTime + " " + bestHeight);
    }
}
