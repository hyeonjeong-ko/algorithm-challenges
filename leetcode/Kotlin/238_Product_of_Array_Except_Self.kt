"""
A Solution to Product of Array Except Self Problem

Metadata:
- Difficulty: Medium
- Time Taken: 30 min

Analysis:
- Input:
  - A list of integers, nums, with a length of n (n > 1).
  - Example input: [1, 2, 3, 4]
- Output:
  - A list of integers where each element is the product of all other elements in the array.
  - Example output: [24, 12, 8, 6] (product of all elements except self for each position)
- Constraints:
  - Do not use division.
  - Achieve a time complexity of O(n).

Implementation:
- Data Structures:
  - List to store intermediate products.
- Explanation:
  1. Left Products: Traverse the list from left to right, calculating the product of all elements to the left of each index.
  2. Right Products: Traverse the list from right to left, updating the result by multiplying the product of elements to the right of each index.
  3. Result Combination: The final list is the product of left and right products for each index.
"""
class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        // 왼쪽 곱셈
        var p = 1
        for (i in nums.indices) {
            result[i] = p
            p *= nums[i] // 왼쪽 곱셈 결과
        }
        // 오른쪽 곱셈, 왼쪽 곱셈 결과에 차례대로 곱하기
        p = 1
        for (i in nums.indices.reversed()) {
            result[i] *= p
            p *= nums[i]
        }
        return result
    }
}
