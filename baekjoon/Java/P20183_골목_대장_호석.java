import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Key Point; 최소의 최댓값 or 최대의 최솟값 == 이분탐색 + 조건판별
 * Algorithms; BinarySearch + Dijkstra
 */

public class P20183_골목_대장_호석 {
    static int N, M, A, B;
    static long C;
    static List<Edge>[] graph;
    static List<int[]> roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        roads = new ArrayList<>(); // u, v, cost 저장
        int maxCost = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            roads.add(new int[] {u, v, cost});
            maxCost = Math.max(maxCost, cost);
        }

        int left = 0, right = maxCost;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            buildGraph(mid);
            if (dijkstra(A, B)) {
                answer = mid;
                right = mid - 1; // 더 작은 수치심도 가능한지 탐색
            } else {
                left = mid + 1; // 안되면 수치심 허용치 더 늘려야함
            }
        }
        System.out.println(answer);
    }

    private static boolean dijkstra(int start, int end) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);


        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
        pq.add(new Edge(start, 0)); // 시작점 초기화 (노드, 비용)
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (dist[now.to] < now.cost) continue;

            // 연결 엣지 모두 탐색
            for (Edge next : graph[now.to]) {
                long newCost = dist[now.to] + next.cost;
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new Edge(next.to, (int) newCost));
                }
            }
        }

        return dist[end] <= C;
    }

    private static void buildGraph(int maxCost) {
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) { // arrayList 초기화
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], cost = road[2];
            if (cost <= maxCost) {
                graph[u].add(new Edge(v, cost)); // 양방향
                graph[v].add(new Edge(u, cost));
            }
        }
    }

    static class Edge {
        int to, cost;
        public Edge (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

    }
}
