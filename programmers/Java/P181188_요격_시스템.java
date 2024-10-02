/*
요격 미사일 문제 풀이

분석
입력:
- 각 미사일의 x축 범위를 나타내는 `targets` 배열이 주어짐
- 미사일은 [시작 지점, 끝 지점] 형태로 주어짐
- 미사일을 최소한의 요격 미사일로 모두 요격해야 함

구현
자료 구조:
- 정수(`curr_e`): 현재 요격 미사일이 커버할 수 있는 끝 지점

알고리즘:
- 미사일을 끝나는 지점을 기준으로 오름차순 정렬
- 정렬된 배열을 순회하며, 현재 요격 미사일이 커버할 수 없는 미사일이 나타날 때마다 새로운 요격 미사일 발사
- 새로운 요격 미사일을 발사하면 커버할 수 있는 끝 지점을 업데이트

결과
시간 복잡도: O(N log N)
- 미사일을 끝나는 지점 기준으로 정렬하는 데 O(N log N)
- 정렬된 미사일을 순회하며 요격 미사일을 발사하는 데 O(N)

추가
- 핵심: 끝나는 지점을 기준으로 그리디하게 선택하여 최소한의 요격 미사일로 모든 미사일을 처리
- 새로운 미사일이 현재 요격 범위를 벗어날 때만 새로운 미사일 발사
*/
import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        int curr_e = 0; // 현재 요격 미사일이 커버할 수있는 끝범위
        
        Arrays.sort(targets, (a,b) -> Integer.compare(a[1], b[1]));
        
        for (int[] target : targets) {
            // 현재 요격 미사일이 커버할 수 없는 범위
            if (target[0] >= curr_e) {
                answer++;
                curr_e = target[1]; // 미사일 끝 지점 업데이트
            }
        }
        
        return answer;
    }
}
