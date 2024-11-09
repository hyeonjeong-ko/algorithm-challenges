// HashMap 기본동작 숙지.

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> m = new HashMap<>();
        
        for (String name : participant) { // 맵으로 참여자 저장
            m.put(name, m.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion) { // 완주자 명단 맵에서 제거
            if (m.get(name) != 0) 
                m.put(name, m.get(name) - 1);
        }
        
        for (String name : m.keySet()) {
            if(m.get(name) != 0)
                return name;
        }
        return "";
    }
}
