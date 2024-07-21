"""
A solution to the 3Sum problem.

Metadata

Difficulty: Medium
Time Taken: 1 hr
Analysis
Input:

List of integers nums
Example: [-1, 0, 1, 2, -1, -4]
Expected Time Complexity: O(N^2)

Implementation

Data Structures:
List for storing results
Algorithms:
Sorting for easier handling of duplicates
Two-pointer technique for finding pairs
Result
Time Complexity: O(N^2)

More

Key insights:
Sorting: First, sort the array to handle duplicates more easily and use two pointers effectively.
Two-pointer technique: After fixing one element, use two pointers to find the other two elements that sum up to zero.
Avoid duplicates: Skip over duplicate values for both the fixed element and the two-pointer elements to ensure unique triplets.
"""
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        ans = []
        for i in range(len(nums)-2):
            if i > 0 and nums[i] == nums[i-1]: # 중복 값 건너뛰기
                continue

            l, r = i + 1, len(nums) - 1
            while l < r:
                sum_val = nums[i] + nums[l] + nums[r]

                if sum_val == 0:
                    ans.append([nums[i],nums[l],nums[r]])

                    # Skip duplicate values for the second element
                    while l < r and nums[l] == nums[l + 1]:
                        l += 1                
                    # Skip duplicate values for the third element
                    while l < r and nums[r] == nums[r - 1]:
                        r -= 1
                    l += 1
                    r -= 1  

                elif sum_val > 0:
                    r -= 1
                elif sum_val < 0:
                    l += 1
        return ans
