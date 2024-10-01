import java.util.HashMap;
import java.util.Map;

class Solution {
    public static long solution(int[] weights) {
        long answer = 0;

        // 1. 몸무게별 빈도를 계산하는 맵 (HashMap)
        Map<Integer, Integer> weightCount = new HashMap<>();
        for (int weight : weights) {
            weightCount.put(weight, weightCount.getOrDefault(weight, 0) + 1);
        }

        // 2. 같은 몸무게끼리 짝을 찾는 경우 (조합 C(n,2))
        for (int weight : weightCount.keySet()) {
            int count = weightCount.get(weight);
            if (count > 1) {
                answer += ((long) count * (count - 1)) / 2;
            }
        }

        // 3. 비율을 고려한 짝 찾기 (3/4, 4/3, 2/3, 3/2, 2/4, 4/2)
        int[] numerators = {3, 4, 2, 3, 2, 4};      // 분자
        int[] denominators = {4, 3, 3, 2, 4, 2};    // 분모

        // 비율을 고려한 몸무게 짝 찾기
        for (int weight : weightCount.keySet()) {
            for (int i = 0; i < numerators.length; i++) {
                int numerator = numerators[i];
                int denominator = denominators[i];

                // 다른 거리에 앉은 경우: weight * numerator가 denominator로 나누어떨어지는지 확인 (정수인지)
                if (((long) weight * numerator) % denominator == 0) {
                    long targetWeight = ((long) weight * numerator) / denominator;

                    // 중복 카운팅 방지: targetWeight가 weight보다 클 때만 카운트
                    if (targetWeight > weight && weightCount.containsKey((int) targetWeight)) {
                        answer += ((long) weightCount.get(weight)) * weightCount.get((int) targetWeight);
                    }
                }
            }
        }

        return answer;
    }
}
