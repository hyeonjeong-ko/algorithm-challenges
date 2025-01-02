// Simul
// https://school.programmers.co.kr/learn/courses/30/lessons/77486?language=java
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        // enroll : referral
        Map<String, String> parent = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
        }
        
        // 각 회원별 수익
        Map<String, Integer> total = new HashMap<>();
        for (String name : enroll)
            total.put(name, 0);
        
        // 판매자별 이익 분배
        for (int i = 0; i < seller.length; i++) {
            int money = amount[i] * 100; // 판매 이익
            String curName = seller[i];
            
            // 상위 노드로 이익 분배
            while (money > 0 && !curName.equals("-")) {
                int distributed = money / 10;
                int remaining = money - distributed;
                
                total.put(curName, total.get(curName) + remaining);
                money = distributed;
                curName = parent.getOrDefault(curName, "-");
            }
        }
        
        // 결과 배열에 매핑
        for (int i = 0; i < enroll.length; i++)
            answer[i] = total.get(enroll[i]);
        return answer;
    }
}
