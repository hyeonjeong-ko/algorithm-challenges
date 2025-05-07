import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P131130_혼자_놀기의_달인 {

    static boolean[] visited;

    public int solution(int[] cards) {
        int answer = 0;

        int n = cards.length;
        visited = new boolean[n];
        List<Integer> groupSizes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = dfs(i,cards);
                groupSizes.add(size);
            }
        }

        if (groupSizes.size() < 2) return 0;

        // 내림차순 정렬
        groupSizes.sort(Collections.reverseOrder());

        return groupSizes.get(0) * groupSizes.get(1);
    }

    private int dfs(int idx,int[] cards) {
        visited[idx] = true;
        int nextIdx = cards[idx] - 1;

        if (!visited[nextIdx]) {
            return 1 + dfs(nextIdx,cards);
        }
        return 1;
    }
}
