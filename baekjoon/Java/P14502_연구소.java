import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// algo: bfs, combi
// 유의할것: comb, Arrays.copyOf(arr[], length)

public class P14502_연구소 {
    static int N, M;
    static int[][] arr;
    static List<int[]> empty = new ArrayList<>(); // '0'칸
    static List<int[]> virus = new ArrayList<>(); // '2'칸
    static int maxSafe = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        // 입력받기 빈칸 + 바이러스 위치(i,j) 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    empty.add(new int[]{i, j});
                } else if (arr[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        // 벽 3개 조합 만들기
        comb(0, 0, new int[3]);

        System.out.println(maxSafe);
    }

    // 조합: 빈 칸 중 3개 고르기
    static void comb(int depth, int start, int[] selected) {
        if (depth == 3) { // 종료조건
            int[][] copy = copyMap(arr);
            for (int idx : selected) {
                int[] pos = empty.get(idx);
                copy[pos[0]][pos[1]] = 1;// 벽세우기
            }

            spreadVirus(copy); // 바이러스 확산
            int safe = countSafe(copy); // 안전 영역 계산
            maxSafe = Math.max(maxSafe, safe);
            return;
        }

        for (int i = start; i < empty.size(); i++) {
            selected[depth] = i;
            comb(depth + 1, i + 1, selected);
        }
    }

    private static int countSafe(int[][] map) {
        int cnt = 0;
        for (int[] row : map) {
            for (int cell : row) {
                if (cell == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void spreadVirus(int[][] map) {
        Queue<int[]> q = new LinkedList<>(); // q 초기화
        for (int[] v : virus) {
            q.add(new int[]{v[0], v[1]});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) { // 상하좌우탐색
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) { // 범위내
                    map[nx][ny] = 2; // 전염
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static int[][] copyMap(int[][] original) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            newMap[i] = Arrays.copyOf(original[i], M);
        }
        return newMap;
    }
}
