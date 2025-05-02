# two pointer
# 조건 만족 시 -> left 줄이고, 조건 미만 시 -> right 늘리기

class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        n = len(nums)
        left = 0
        total = 0
        min_len = float('inf')

        for right in range(len(nums)):
            total += nums[right]

            # 조건 만족할 때까지 왼쪽 줄이기
            while total >= target:
                min_len = min(min_len, right - left + 1)
                total -= nums[left]
                left += 1

        return 0 if min_len == float('inf') else min_len
