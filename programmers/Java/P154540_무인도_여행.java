// bfs,dfs
import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int M, N, nx, ny, count;
    static int[][] graph; // 숫자값 저장

    public int[] solution(String[] maps) {
        M = maps.length;
        N = maps[0].length();
        visited = new boolean[M + 1][N + 1];
        graph = new int[M + 1][N + 1];

        // maps를 숫자 배열 graph로 변환
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i].charAt(j) == 'X') {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Character.getNumericValue(maps[i].charAt(j));
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        // DFS를 이용해 각 섬의 자원 합산
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    res.add(count);
                    count = 0;
                }
            }
        }

        // 섬이 없으면 -1 반환
        if (res.isEmpty()) {
            res.add(-1);
        } else {
            Collections.sort(res);
        }

        // 결과를 배열로 변환하여 반환
        int[] answer = res.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    // 깊이 우선 탐색(DFS) 함수
    public static void dfs(int x, int y) {
        visited[x][y] = true;
        count += graph[x][y];

        // 상하좌우로 이동하며 탐색
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            // 유효 범위 내에 있고, 방문하지 않은 경우
            if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                if (!visited[nx][ny] && graph[nx][ny] != 0) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
