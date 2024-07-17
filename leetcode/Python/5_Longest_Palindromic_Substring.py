"""
A solution to the palindrome checking problem using string expansion.

Metadata
Difficulty: Medium
Time Taken: 40 min

Analysis
Input: 
- A string s

Expected Time Complexity: O(n^2)
where n is the length of the string

Implementation
Data Structures: String Slicing
Algorithms: Expand Around Center, String Manipulation
Statements: for loop, while loop

Result
Time Complexity: O(n^2)

More
- Key insights: Using the expand around center technique to efficiently find the longest palindromic substring by expanding from each character(odd) and each pair of characters(even) as the center.
"""

class Solution:
    def longestPalindrome(self, s: str) -> str:
        def expand(left: int, right: int) -> str:
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            return s[left + 1:right]

        if len(s) < 2 or s == s[::-1]:
            return s
        
        result = ''
        # 슬라이딩 윈도우 우측이동
        for i in range(len(s) - 1):
            result = max(result,
                            expand(i,i),
                            expand(i,i + 1),
                            key=len)
        return result
