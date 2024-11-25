import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // 원하는 제품과 개수를 저장할 Map
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        // 10일 간의 할인 내역을 확인
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> discountMap = new HashMap<>();
            
            // 현재 10일 간의 할인 내역을 저장
            for (int j = i; j < i + 10; j++) {
                discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);
            }
            
            // 원하는 제품 구성과 비교
            if (discountMap.equals(wantMap)) {
                answer++;
            }
        }
        
        return answer;
    }
}
