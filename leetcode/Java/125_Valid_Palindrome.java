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

More
- Key insights: Using Comparing primitive char is faster than comparing string objects.
"""

class Solution {
    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while(start < end){
            // 영숫자인지 판별하고 유효하지않으면 한 칸씩 이동
            if(!Character.isLetterOrDigit(s.charAt(start))){
                start++;
            } else if(!Character.isLetterOrDigit(s.charAt(end))){
                end--;
            } else{ // 유효한 문자라면 앞,뒷 글자를 모두 소문자로 변경해 비교
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    //하나라도 일치하지 않으면 팰린드롬X
                    return false;
                }
                //앞쪽 문자는 한칸 뒤로,뒤쪽 문자는 한칸 앞으로
                start++;
                end--;
            }
        }
        return true;
        /**
        풀이 2. 정규식 풀이
       
        //정규식으로 유효문자만 추출, 모두 소문자로 변경
        String s_filtered = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        //문자열 뒤집고 String으로 변경
        String s_reversed = new StringBuilder(s_filtered).reverse().toString();
        // 펠린드롬 체크
        return s_filtered.equals(s_reversed); 
         */
    }
}
