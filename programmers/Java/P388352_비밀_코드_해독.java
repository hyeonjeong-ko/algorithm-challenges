import java.util.*;

public class 비밀_코드_해독 {
    /**
     * 가능한 비밀 코드(5개 조합)의 개수를 구한다.
     * @param n   숫자 범위 상한 (1~n)
     * @param q   사용자가 시도한 숫자들(m×5 배열)
     * @param ans 각 시도에서 맞힌 개수 배열
     * @return    조건을 모두 만족하는 조합의 총 개수
     */
    public int solution(int n, int[][] q, int[] ans) {
        // 1) q 배열을 Set으로 변환 (교집합 연산용)
        List<Set<Integer>> querySets = new ArrayList<>();
        for (int[] arr : q) {
            Set<Integer> s = new HashSet<>();
            for (int x : arr) {
                s.add(x);
            }
            querySets.add(s);
        }

        // 2) 1~n 중 5개 뽑는 모든 조합 생성
        List<Set<Integer>> candidates = new ArrayList<>();
        combination(1, n, 5, new ArrayList<>(), candidates);

        // 3) 각 조합별로 시도 결과와 비교하여 유효성 검사
        int count = 0;
        for (Set<Integer> candidate : candidates) {
            if (isValid(candidate, querySets, ans)) {
                count++;
            }
        }

        return count;
    }

    /**
     * 백트래킹으로 r개짜리 조합을 모두 생성해 result에 담는다.
     * @param start   이번 단계에서 고를 수 있는 최소 숫자
     * @param n       숫자 범위 상한
     * @param r       조합 크기
     * @param current 현재까지 선택한 숫자 리스트
     * @param result  완성된 조합을 저장할 리스트
     */
    private void combination(int start, int n, int r,
                             List<Integer> current,
                             List<Set<Integer>> result) {
        if (current.size() == r) {
            result.add(new HashSet<>(current));
            return;
        }
        // 남은 숫자가 부족하지 않도록 i의 최댓값 계산
        int max = n - (r - current.size()) + 1;
        for (int i = start; i <= max; i++) {
            current.add(i);
            combination(i + 1, n, r, current, result);
            current.remove(current.size() - 1);  // 백트래킹
        }
    }

    /**
     * 후보 조합이 모든 시도 결과(ans)와 일치하는지 검사
     * @param candidate 5개 숫자 집합
     * @param querySets 시도별 숫자 집합 리스트
     * @param ans       시도별 맞힌 개수 배열
     * @return          모든 시도에서 교집합 크기가 ans[i]와 같으면 true
     */
    private boolean isValid(Set<Integer> candidate,
                            List<Set<Integer>> querySets,
                            int[] ans) {
        for (int i = 0; i < querySets.size(); i++) {
            // candidate와 querySets.get(i)의 교집합 크기 계산
            Set<Integer> tmp = new HashSet<>(candidate);
            tmp.retainAll(querySets.get(i));
            if (tmp.size() != ans[i]) {
                return false;
            }
        }
        return true;
    }
}
