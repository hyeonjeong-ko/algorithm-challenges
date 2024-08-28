/*
A solution to maximize profit from stock prices by buying and selling on different days.

Metadata
- **Difficulty**: Easy to Medium
- **Time Taken**: 20 Min

Analysis
**Input**:
- A list of prices, where prices[i] represents the stock price on the ith day

**Expected Time Complexity**: O(n), where 1 <= n <= 100,000

**Implementation**
- **Data Structures**:
  - Two pointers, l and r, representing buy and sell days
- **Algorithms**:
  - Single-pass traversal:
    1. Adjust the buy day if a lower price is found
    2. Calculate profit and update the maximum profit if the current profit is higher
*/
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int l=0, r=1;

        while(r < prices.length) {
            if(prices[r] < prices[l]) {
                l = r;
            } else {
                maxProfit = Math.max(maxProfit, prices[r]-prices[l]);
                r++;
            }
        }
        return maxProfit;
    }
}
