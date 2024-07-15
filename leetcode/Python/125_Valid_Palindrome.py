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
Data Structures: Slice, (Deque)
Algorithms: String Manipulation
Statements: for loop, while loop

Result
Time Complexity: O(n)

More
- Key insights: Using Slice for efficient palindrome check. Using deque for efficient popping from both ends;
"""
class Solution:
    def isPalindrome(self, s: str) -> bool:
        strs = []
        s = s.lower()
        for char in s:
            if char.isalnum(): # 영문자,숫자 여부 # re.sub('[^a-z0-9]','',s)
                strs.append(char)
        return ''.join(strs) == ''.join(strs[::-1])

        """
        풀이 2 Deque
        while len(strs) > 1:
          if strs.popleft() != strs.pop():
            return False
          return True
        """

        """
        풀이 3 two pointer
        l, r = 0, len(s)-1
        
        while l < r:
            while l < r and not self.alphaNum(s[l]):
                l+=1
            while r > l and not self.alphaNum(s[r]):
                r-=1
            if s[l].lower() != s[r].lower():
                return False
            l, r = l+1,r-1
        return True
        
    def alphaNum(self,c):
        return (ord('A') <= ord(c) <= ord('Z') or
        ord('a')<=ord(c)<=ord('z') or
        ord('0') <= ord(c)<=ord('9'))
        """
    
