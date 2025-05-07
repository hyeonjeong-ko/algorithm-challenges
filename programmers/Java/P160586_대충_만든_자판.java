import java.util.HashMap;
import java.util.Map;

public class P160586_대충_만든_자판 {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<Character, Integer> pressMap = new HashMap<>();

        // 문자별 최소 누름 수 저장
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                int pressCount = i + 1;

                if (!pressMap.containsKey(ch)) {
                    pressMap.put(ch, pressCount);
                } else {
                    pressMap.put(ch, Math.min(pressMap.get(ch), pressCount));
                }
            }
        }

        // 각 target마다 누름 수 계산
        for (int i = 0; i < targets.length; i++) {
            String word = targets[i];
            int total = 0;

            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (!pressMap.containsKey(ch)) { // 해당문자 없으면 -1
                    total = - 1;
                    break;
                }
                total += pressMap.get(ch);
            }

            answer[i] = total;
        }
        return answer;
    }
}
