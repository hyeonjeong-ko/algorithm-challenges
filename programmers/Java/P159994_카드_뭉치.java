/**
Queue 자료구조 사용; O(N + M)
- card1과 card2는 무조건 앞부터 사용해야 함.
- 순서를 뒤바꿀 수 없음.
*/
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> qCards1 = new LinkedList<>();
        Queue<String> qCards2 = new LinkedList<>();
        Queue<String> qGoal = new LinkedList<>();
        
        // 카드 리스트를 큐로 변환
        for (String card : cards1)
            qCards1.add(card);

        for (String card : cards2)
            qCards2.add(card);
        
        for (String word : goal)
            qGoal.add(word);
        
        while (!qGoal.isEmpty()) {
            String cur = qGoal.poll();
            
            if (!qCards1.isEmpty() && cur.equals(qCards1.peek())) {
                qCards1.poll();
            } else if (!qCards2.isEmpty() && cur.equals(qCards2.peek())) {
                qCards2.poll();
            } else {
                return "No";
            }
        }
        return "Yes";
    }
}
