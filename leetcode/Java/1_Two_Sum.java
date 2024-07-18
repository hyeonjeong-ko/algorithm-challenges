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
  
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // target - nums 뺀 값이 있으면 정답 리턴
            if (numsMap.containsKey(target - nums[i])) {
                return new int[] {numsMap.get(target-nums[i]), i};
            }
            // 다음 비교를 위해 인덱스를 맵에 저장
            numsMap.put(nums[i], i);
        }
        // 항상 정답존재하므로 널 리턴경우는 없다.
        return null;
        }
    }
