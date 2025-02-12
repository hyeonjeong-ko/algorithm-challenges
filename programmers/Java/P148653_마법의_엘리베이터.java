// dp

class Solution {
    public int solution(int storey) {
        // 기저 조건: storey가 한 자리 수이면
        if (storey < 10) {
            // 한 자리 수인 경우, 5 이하이면 그대로, 6 이상이면 11 - storey가 최소 비용
            return storey <= 5 ? storey : 11 - storey;
        }
        
        // 현재 자리(1의 자리)의 숫자
        int digit = storey % 10;
        // 두 가지 선택지를 고려:
        // 1. 현재 자릿수를 내림: 현재 자리의 숫자(digit)만큼 비용 소모하고,
        //    상위 자리로 넘어감 → solution(storey / 10)
        // 2. 현재 자릿수를 올림: (10 - digit)만큼 비용 소모하고,
        //    상위 자리 값에 1을 올림하여 반영 → solution((storey / 10) + 1)
        return Math.min(digit + solution(storey / 10),
                        (10 - digit) + solution((storey / 10) + 1));
    }
}
