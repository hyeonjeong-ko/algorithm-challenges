/*
시간초과 주의.딕셔너리 사용해 시간초과 개선.
O(n)->O(1)
*/

import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        // 각 플레이어 현재 인덱스 기록 맵
        Map<String, Integer> rank = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i); // 선수명: 현재위치인덱스
        }
        
        // 호출된 순서대로 선수들을 업데이트
        for (String name : callings) {
            int idx = rank.get(name); // 추월 플레이어 인덱스
            
            String prev = players[idx-1]; // 추월당한 선수명

            // players배열에서 위치 변경
            players[idx] = prev;
            players[idx-1] = name;
            
            // 맵에서 인덱스 업데이트
            rank.put(name, idx-1);
            rank.put(prev, idx);
        }
        return players;

    }
}
