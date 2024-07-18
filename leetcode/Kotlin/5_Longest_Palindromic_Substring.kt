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
                Kotlin requires declaring 'var' separately to avoid modifying the parameter.
"""

class Solution {
    var left = 0
    var maxLen = 0
    
    fun extendPalindrome(s: String, j: Int, k: Int) {
        // 파라미터는 수정 할 수 없으므로 var 별도 선언
        var l = j
        var r = k
        while(l >= 0 && r < s.length && s[l] == s[r]) {
            l--
            r++
        }
        // 기존 최대 길이보다 큰 경우 값 교체
        if (maxLen < r - l - 1) { // (r - 1) - (l + 1) + 1
            left = l + 1
            maxLen = r - l - 1
        }

    }
    
    fun longestPalindrome(s: String): String {
        // 문자 길이 저장
        var len = s.length
        
        // 길이 1 예외처리
        if (len < 2) return s

        // 우측으로 한 칸씩  이동하며 투 포인터 조사
        for (i in 0 until len -  1) {
            extendPalindrome(s, i, i) // 홀수 투포인터
            extendPalindrome(s, i, i + 1) // 짝수 투포인터
        }
        return s.substring(left, left + maxLen)
    }
}
