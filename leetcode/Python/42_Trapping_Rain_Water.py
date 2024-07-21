"""
A solution to the Trapping Rain Water problem.

Metadata
Difficulty: Hard
Time Taken: 2 hr
Analysis
Input:

A list of integers height representing the heights of bars in a histogram (e.g., [0,1,0,2,1,0,1,3,2,1,2,1]).
Expected Time Complexity: O(N), O(N^2)(brute-force)

Implementation

Data Structures:
Two pointers
Algorithms:
Two-pointer technique to calculate trapped water by maintaining maximum height from left and right sides.
Result
Time Complexity: O(N)
More
Key insights:
Use two pointers to traverse the list from both ends towards the center.
Maintain max_left and max_right to store the highest bar encountered from the left and right respectively.
Compute trapped water based on the minimum of max_left and max_right at each step, adjusting pointers and maximums accordingly.
"""


class Solution:
    def trap(self, height: List[int]) -> int:
        l, r = 0, len(height) - 1
        max_left, max_right = height[l], height[r]
        ans = 0

        while l < r:
            if max_left <= max_right:
                ans += max_left - height[l]
                l += 1
                max_left = max(max_left,height[l])
            else:
                ans += max_right - height[r]
                r -= 1
                max_right = max(max_right,height[r])
        return ans
