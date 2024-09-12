/*
주어진 테이블의 데이터를 정렬하고 XOR 연산을 통해 해시 값을 계산하는 문제.

메타데이터:
- **난이도**: 중간
- **소요 시간**: 20min

분석:

구현:
- **알고리즘**:
  - 간단 구현 문제
  - `col` 열을 기준으로 오름차순 정렬, 값이 같다면 첫 번째 열을 기준으로 내림차순 정렬.
  - `[row_begin, row_end]` 범위의 각 행에 대해, 해당 행 번호로 나눈 나머지 값을 더한 결과를 계산.
  - 이 값을 XOR 연산하여 최종 해시 값을 구함.
*/

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        /*
        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] a,int[] b) {
                if (a[col-1] == b[col-1]) {
                    // 첫 번째 열 기준 내림차순
                    return Integer.compare(b[0],a[0]);
                } else {
                    // col-1 기준 오름차순
                    return Integer.compare(a[col-1],b[col-1]);
                }
            }
        });
        */
        Arrays.sort(data, (a, b) -> {
            if (a[col-1] == b[col-1]) {
                return Integer.compare(b[0],a[0]); // 내림차순
            } else {
                return Integer.compare(a[col-1],b[col-1]); // 오름차순
            }
        });
        int answer=0;
        // 2. row_begin ~ row_end 범위의 각 행에 대해 S_i 계산 및 XOR 연산
        for (int i = row_begin - 1; i < row_end; i++) {
            int s_i = 0;
            for (int val : data[i]) {
                s_i += val % (i + 1);
            }
            answer^=s_i; // XOR 연산
        }
        return answer;
    }
}
