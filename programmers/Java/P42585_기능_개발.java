// Queue 자료구조

// Sol 1. arrayList 이용풀이

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < progresses.length; i++) {
            double days = (100 - progresses[i]) / (double) speeds[i];
            int daysUp = (int) Math.ceil(days);
            
            int count = 1;  // 현재 기능 포함
            int j = i + 1;
            
            for(; j < progresses.length; j++) {
                if (progresses[j] + daysUp * speeds[j] >= 100) {
                    count++;
                } else {
                    break;
                }
            }
            
            ans.add(count);
            i = j - 1;  // 반복문을 위한 인덱스 조정
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}


// Sol 2. queue 이용풀이
import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < progresses.length; i++) {
           int remainingDays = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            q.add(remainingDays);
        }
        
        while (!q.isEmpty()) {
            int days = q.poll(); // 배포 기준 일수
            int cnt = 1; // 배포 기능 개수
            
            while (!q.isEmpty() && q.peek() <= days) {
                q.poll();
                cnt++;
            }
            ans.add(cnt);    
        }
        
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
