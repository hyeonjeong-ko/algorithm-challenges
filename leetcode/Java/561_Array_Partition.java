/*
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
*/
class Solution {
    public int arrayPairSum(int[] nums) {
        int sum = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) sum += nums[i];
        }
        return sum;

        /*
        int sum = 0;
        List<Integer> pair = new ArrayList<>();
        Arrays.sort(nums);

        for (int n : nums) {
            pair.add(n);
            if (pair.size() == 2) {
                sum += Collections.min(pair);
                pair.clear();
            }
        }
        return sum;
        */
    }
}
