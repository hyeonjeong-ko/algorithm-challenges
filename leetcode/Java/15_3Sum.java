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
**/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int l, r, sum;
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 중복 값 건너뛰기
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            l = i + 1;
            r = nums.length -1;
            while (l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) l += 1;
                else if (sum > 0) r -= 1;
                else{
                    results.add(Arrays.asList(nums[i],nums[l],nums[r]));

                    //중복된 값 건너뛰기 (답 중복 방지)
                    while (l < r && nums[l] == nums[l + 1]) l += 1;
                    while (l < r && nums[r] == nums[r - 1]) r -= 1;
                    l += 1;
                    r -= 1;
                }
            }
        }
        return results;
    }
}
