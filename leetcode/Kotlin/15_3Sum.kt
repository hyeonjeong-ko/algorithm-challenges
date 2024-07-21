/**
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
*/
class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        var l: Int
        var r: Int
        var sum: Int
        val results: MutableList<List<Int>> = mutableListOf()
        Arrays.sort(nums)

        for (i in 0 until nums.size - 2) {
            if (i > 0 && nums[i] == nums[i - 1]) continue

            l = i + 1
            r = nums.size - 1
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r]
                if (sum < 0) l += 1
                else if(sum > 0) r -= 1
                else {
                    results.add(listOf(nums[i],nums[l],nums[r]))

                    while (l < r && nums[l] == nums[l + 1]) l += 1
                    while (l < r && nums[r] == nums[r - 1]) r -= 1

                    // 합이 0이므로 양 쪽 모두 이동해야 한다.
                    l += 1
                    r -= 1
                }
            }
        }
        return results
    }
}
