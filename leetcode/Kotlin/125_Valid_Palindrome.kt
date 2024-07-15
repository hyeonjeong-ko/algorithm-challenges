"""
A solution to the palindrome checking problem using isalnum() method.

Metadata
Difficulty: Easy
Time Taken: 10 min

Analysis
Input: 
- A string s

Expected Time Complexity: O(n)
where n is the length of the string

Implementation
Algorithms: String Manipulation
Statements: for loop, while loop

Result
Time Complexity: O(n)
"""

class Solution {
    fun isPalindrome(s: String): Boolean {
        var start = 0
        var end = s.length - 1
        // 서로 중앙으로 이동해 나가다 겹치는 지점에 도달하면 종료
        while (start < end) {
            when {
                // 영숫자인지 판별하고 아니라면 한 칸 이동
                !Character.isLetterOrDigit(s[start]) -> start++
                !Character.isLetterOrDigit(s[end]) -> end--
                else -> {
                    // 유효문자들은 앞뒤 글자를 모두 소문자로 변경해 비교
                    if (Character.toLowerCase(s[start]) != Character.toLowerCase(s[end])) {
                        return false
                    }
                    start++
                    end--
                }
            }
        }
        return true // 모든 문자가 일치
    }
}
