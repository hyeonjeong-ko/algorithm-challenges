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
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val numsMap: MutableMap<Int, Int> = mutableMapOf()
        
        for ((i, num) in nums.withIndex()) {
            // target-num 있으면 정답으로 리턴
            if (numsMap.containsKey(target - num)) {
                return intArrayOf(numsMap[target - num] ?: 0, i) // (elvis operator)
            }
            numsMap[num] = i // 다음 비교를 위해 인덱스 맵에 저장
        }
        return intArrayOf(0, 0)
    }
}
