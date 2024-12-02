"""
인접하지 않은 집들을 털어 최대한 많은 돈을 훔칠 수 있는 최대 금액을 반환하는 문제
dp
"""


class Solution:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        if len(nums) == 1:
            return nums[0]

        # dp 배열 초기화
        dp = [0] * len(nums)  # i번째 집까지 털었을때 최대 수익
        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        # 집들을 순회하며 최대 이익 계산
        for i in range(2, len(nums)):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])

        return dp[-1]
