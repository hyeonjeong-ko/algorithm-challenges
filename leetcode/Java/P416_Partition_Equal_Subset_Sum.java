// dfs + memo -> dp

/*
DP Set은 숫자들을 하나씩 보면서 만들 수 있는 모든 점수를 기억하고,
목표 점수에 도달하면 성공하는 방법이다.

가방(Set) 시작 -> {0}
for 숫자 in nums:
    가방 안 점수들 각각에 숫자 더해 -> 새 점수들 만들기
    새 점수들 가방에 추가
    만약 가방 안에 target이 생겼으면 -> 바로 True

핵심 포인트
- DP적 사고 도출: 과정(누가 선택했나)을 버리고, 결과(합계)만 추적하자
- DFS vs DP Set 차이: DFS는 (index, 합)을 기억 / DP Set은 (합)만 기억
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;

        if (total % 2 != 0) return false;

        int target = total / 2;

        Set<Integer> dp = new HashSet<>();
        dp.add(0); // 초기: 0점

        for (int num : nums) { // 기존 dp + num 합하며 나올수있는 nextDp 구하기
            Set<Integer> nextDp = new HashSet<>();

            for (int t : dp) {
                if (t + num == target) return true;
                nextDp.add(t); // 현재 점수 그대로 유지
                nextDp.add(t + num); // 현재 숫자를 추가한 점수
            }
            dp = nextDp; // 다음 숫자 볼 때 새로운 점수들로 갱신
        }
        return dp.contains(target);
    }
}
