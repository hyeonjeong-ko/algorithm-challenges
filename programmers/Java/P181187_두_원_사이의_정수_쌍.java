/*
**핵심 아이디어:**

- **대칭성 활용**: 원은 대칭적이므로 한 사분면의 점들만 계산하고 전체 점의 개수를 구할 때 4를 곱
- **정수 좌표 개수 산출**: `math.floor`와 `math.ceil` 함수를 사용
- **정수 y 값의 개수 계산**:
  - 가능한 y 값의 개수는 `floor(max_r2) - ceil(max_r1) + 1`
  - `+1`은 양 끝 값도 포함하기 위해 필요
*/
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for (int x = 1; x <= r2; x++) {
            // 큰 원의 y 최대값 계산
            double max_r2 = Math.sqrt((long)r2*r2 - (long)x*x);
            // 작은 원의 y 최대값 계산
            double max_r1 = x > r1? 0 : Math.sqrt((long)r1*r1 - (long)x*x);
            // 정수 y 값 최대값과 최소값 구하기
            long y_max = (long) Math.floor(max_r2);
            long y_min = (long) Math.ceil(max_r1);
            
            answer += y_max - y_min + 1;
        }
        return answer*4;
        
    }
}
