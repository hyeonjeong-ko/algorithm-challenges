// two pointer
// 투포인터 전체 탐색돌면서 ans 갱신하는것 잊지말기!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken());

        int[] arr = new int[N]; // 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int bestSum = Integer.MAX_VALUE;
        int ansLeft = 0;
        int ansRight = 0;

        while (left < right){
            int currSum = arr[left] + arr[right];

            if (Math.abs(currSum) < bestSum) { // 더 가까운거리 발견하면 답 갱신
                bestSum = Math.abs(currSum);
                ansLeft = arr[left];
                ansRight = arr[right];
            }

            if (currSum > 0) {
                right--; // 합이 양수면 오른쪽 수 줄이기
            } else if (currSum < 0) {
                left++; // 합이 음수면 왼쪽 수 늘리기
            } else {
                break;
            }
        }
        System.out.println(ansLeft + " " + ansRight);

    }
}
