/*
bfs
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_2178 {
    static int N;
    static int M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0}; // 상하
    static int[] dy = {0, 0, -1, 1}; // 좌우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
             String s = br.readLine();
             for(int j = 0; j <M; j++) {
                 arr[i][j] = s.charAt(j) - '0';
             }
        }

        bfs(0,0);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>(); // 큐 생성
        int[][] visited = new int[N][M];
        q.add(new int[] {x,y});
        visited[0][0] = 1;

        while (!q.isEmpty()) {
            int[] n = q.poll();
            int nx = n[0];
            int ny = n[1];

            // 정답 조건 처리
            if (nx == N - 1 && ny == M - 1) {
                System.out.println(visited[nx][ny]);
                return;
            }


            for(int i = 0; i < 4; i ++) { // 방향 탐색
                int nextX = nx + dx[i];
                int nextY = ny + dy[i];

                // 탐색 제한 조건; 범위내, 미방문, 1만 탐색
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                if (visited[nextX][nextY] != 0 || arr[nextX][nextY] == 0) continue;

                q.add(new int[] {nextX, nextY});
                visited[nextX][nextY] = visited[nx][ny] + 1;
            }

        }
    }

}
