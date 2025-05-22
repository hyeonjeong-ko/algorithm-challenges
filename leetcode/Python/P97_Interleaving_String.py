"""
algorithms: dp
time complexity: O(m * n)
key point:
dp[i][j] = True
→ s1의 앞 i글자, s2의 앞 j글자를 섞어서 s3의 앞 i+j글자를 만들 수 있는가?
"""

class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        if len(s1) + len(s2) != len(s3):
            return False

        # dp 초기화
        dp = [[False] * (len(s2) + 1) for _ in range(len(s1) + 1)]
        dp[0][0] = True # ""

        # 첫째 행,열 초기화
        for i in range(1, len(s1) + 1):
            dp[i][0] = dp[i-1][0] and s1[i-1]==s3[i-1]
        for j in range(1, len(s2) + 1):
            dp[0][j] = dp[0][j-1] and s2[j-1]==s3[j-1]
        
        # 나머지 칸 채우기
        for i in range(1, len(s1) + 1):
            for j in range(1, len(s2) + 1):
                # s1 글자 쓴 경우
                if dp[i-1][j] and s1[i-1] == s3[i+j-1]:
                    dp[i][j] = True
                if dp[i][j-1] and s2[j-1] == s3[i+j-1]:
                    dp[i][j] = True
        
        return dp[len(s1)][len(s2)]
