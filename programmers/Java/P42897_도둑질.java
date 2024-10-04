class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int n = money.length;
        if (n==1) return money[0];
        
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        // 1번집을 훔치는 경우
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0],money[1]);
        
        for (int i = 2; i < n - 1; i++) { // 마지막 집 못훔침
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
        }
        
        // 1번집을 안훔치는 경우
        dp2[0] = 0;
        dp2[1] = money[1];
        
        for (int i = 2; i < n; i++) { // 마지막 집 훔침 가능
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[i]);
        }
        return Math.max(dp1[n-2],dp2[n-1]);        
    }
}
