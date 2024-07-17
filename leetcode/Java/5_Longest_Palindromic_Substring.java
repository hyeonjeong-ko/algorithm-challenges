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


class Solution {
    int max_left, maxLen;
    public void extendPalindrome(String s, int left, int right) {
        // 투 포인터가 유효 범위 내, 양쪽 끝 문자 일치하면 범위 확장
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        // 기존 최대 길이보다 크면 값 교체
        if(maxLen < right - left - 1) {
            max_left = left + 1;
            maxLen = right - left - 1; // (right - 1) - (left + 1) + 1
        }
    }
    
    public String longestPalindrome(String s) {
        // 문자 길이 저장
        int len = s.length();

        // 길이 1이면 반환
        if (len < 2) return s;

        // 우측으로 한 칸씩 이동하며 투 포인터 조사
        for (int i = 0; i < len; i++) {
            extendPalindrome(s, i, i); // 홀수 투포인터
            extendPalindrome(s, i, i + 1); // 짝수 투포인터
        }
        return s.substring(max_left, max_left + maxLen);
    }
}
