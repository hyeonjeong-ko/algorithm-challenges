"""
A solution to the Two Sum problem.

Metadata
Difficulty: Easy
Time Taken: 30 mins

Analysis
Input:
- List of integers `nums`
- Integer `target`
Expected Time Complexity: O(N)

Implementation
Data Structures: Dictionary (HashMap)
Algorithms: Hashing
Statements: for loop, if-else

Result
Time Complexity: O(N)

More
- Key insights: Use a hash map to store the indices of the numbers we have seen so far; Check if the complement (target - current number) exists in the hash map.
"""

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        
        nums_map = {}

        # 키와 값을 바꾸어 딕셔너리로 저장
        for i, num in enumerate(nums):
            if target - num in nums_map: # 타겟 - 첫번째수 뺀 결과를 키로 조회
                return [nums_map[target - num], i]
            nums_map[num] = i

        """
        # brute-force
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                if nums[i] + nums[j] == target:
                    return [i, j]
        """
