"""
A Solution to Array Pair Sum Problem

Metadata:
- Difficulty: Easy
- Time Taken: 20 min
Analysis:
- Input:
  - A list of 2n integers, nums, where n is a positive integer.
  - Example input: [1, 4, 3, 2]
- Output:
  - An integer representing the maximum sum of n pairs formed by the integers.
  - Example output: 4 (formed from pairs (1,2) and (3,4))
- Constraints:
  - The length of nums is always even.
- Expected Time Complexity: O(n log n)

Implementation:
- Data Structures:
  - List to store and sort integers.
- Algorithm:
  1. Sorting: Sort the input list nums in ascending order.
  2. Pair Formation: Use every second element of the sorted list as part of the sum.
"""
class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        return sum(sorted(nums)[::2])

        """
        짝수번째 합
        sum = 0
        nums.sort()

        for i, n in enumerate(nums):
            if i % 2 == 0:
                sum += n
        return sum
        """

        """
        오름차순
        sum = 0
        pair = []
        nums.sort()

        for n in nums:
            # 앞에서부터 오름차순으로 페어를 만들어서 합 계산
            pair.append(n)
            if len(pair) == 2:
                sum += pair[0]
                pair = []
        return sum
        """
