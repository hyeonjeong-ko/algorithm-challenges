"""
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
"""
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        max_profit = 0
        l = 0
        r = 1

        while r < len(prices):
            if prices[r] - prices[l] < 0:
                l = r
            else:
                max_profit = max(prices[r] - prices[l], max_profit)
                r += 1
        return max_profit
