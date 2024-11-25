import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String, String> m = new HashMap<>();
        
        // 1. record 처리: "Enter"와 "Change" 명령어 처리
        for (String line : record) {
            String[] cmd = line.split(" ");
            if(!cmd[0].equals("Leave"))
                m.put(cmd[1],cmd[2]);
        }
        
        // 2. 결과 메시지 생성
        for (String line : record) {
            String[] cmd = line.split(" ");
            if (cmd[0].equals("Enter")) {
                answer.add(m.get(cmd[1]) + "님이 들어왔습니다.");
            } else if (cmd[0].equals("Leave")) {
                answer.add(m.get(cmd[1]) + "님이 나갔습니다.");
            }
        }
        return answer.toArray(new String[0]);        
    }
}
