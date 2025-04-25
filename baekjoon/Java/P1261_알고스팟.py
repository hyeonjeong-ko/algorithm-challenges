// dijkstra
// 가중치를 벽부수면 1로 설정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1261 {
    static int n, m;
    static int[][] maze;
    static int[][] dist;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] wh = br.readLine().split(" ");
        m = Integer.parseInt(wh[0]); // 열 길이
        n = Integer.parseInt(wh[1]); // 행 길이

        maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < m; j++) {
                // '0'은 빈 방, '1'은 벽
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        // dist 배열을 INF 로 초기화
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = INF;
            }
        }

        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue();
        // 시작점 초기화
        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int cw = cur.cost, x = cur.x, y = cur.y;
            if (cw > dist[x][y]) continue;

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위내 탐색
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 다음 칸이 벽이면 +1, 빈 방이면 +0
                int nw = cw + maze[nx][ny];
                if (nw < dist[nx][ny]) {
                    dist[nx][ny] = nw;
                    pq.offer(new Node(nw, nx, ny));
                }
            }
        }
        // 결과 출력: (n-1, m-1)까지 최소 부순 벽 개수
        System.out.println(dist[n-1][m-1]);


    }

    static class Node implements Comparable<Node> {
        int cost, x, y;
        Node (int cost, int x, int y) {
            this.cost = cost;
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Node o2) {
            return Integer.compare(this.cost, o2.cost);
        }
    }
}
