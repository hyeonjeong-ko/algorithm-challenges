/*
스택 활용: 아직 가격이 떨어지지 않은 인덱스를 스택에 저장.
기간 확정: 현재 가격이 떨어질 때, 스택에 쌓인 이전 가격들의 유지 기간을 확정.
남은 가격 처리: 끝까지 떨어지지 않은 가격들은 리스트 끝까지의 거리로 유지 기간 계산.
*/
import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        
        Stack<Integer> st = new Stack<>();
        st.push(0);
        
        for(int i=1; i<n; i++) {
            // 가격이 떨어졌으므로 이전 가격의 기간 계산
            while(!st.isEmpty() && prices[i] < prices[st.peek()]) {
                int j = st.pop();
                ans[j] = i - j;
            }
            st.push(i);
        }
        
        while(!st.isEmpty()){
            int j = st.pop();
            ans[j] = n-1 - j;
        }
        return ans;
    }
}
