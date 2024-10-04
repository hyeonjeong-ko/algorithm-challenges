import java.util.*;

class Solution {
    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) {
                    return false;
                }
            } else { // 여는 괄호는 스택에 추가
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    
    public static int solution(String s) {
        int answer = 0;
        
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            deque.add(c);
        }
        
        for (int i = 0; i < s.length(); i++) {
            // Deque를 String으로 변환
            StringBuilder sb = new StringBuilder();
            for (char c : deque) {
                sb.append(c);
            }
            String rotatedString = sb.toString();
            
            // 유효한 괄호인지 확인
            if (isValid(rotatedString)) {
                answer++;
            }
            
            // 회전
            char first = deque.removeFirst();
            deque.addLast(first);
        }
        
        return answer;
    }
}
