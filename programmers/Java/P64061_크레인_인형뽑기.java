// stack

import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        
        Stack<Integer> st = new Stack<>();
        
        // 인접리스트 생성 및 초기화
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // 아래에서 위로 인형 추가
        for (int row = n-1; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != 0)
                    adj[col].add(board[row][col]); // 인형 없는 경우는 푸시 X
            }
        }
        
        for (int i=0; i < moves.length; i++) {
            int col = moves[i] - 1; // 0-based
            
            if (!adj[col].isEmpty()) {
                int e = adj[col].remove(adj[col].size()-1);
                if (!st.isEmpty() && st.peek() == e) {
                    answer+=2;
                    st.pop();
                } else {
                    st.push(e);
                }
            }
        }
        
        return answer;
    }
}
