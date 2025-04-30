import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P11779_최소비용구하기 {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<Edge>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v,w)); // 도착노드, 비용
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // Dijkstra
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        dist[A] = 0;
        pq.offer(new int[]{0, A}); // 누적비용, 노드

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], cur_node = cur[1];

            if (cost > dist[cur_node]) continue;

            for (Edge e : adj[cur_node]) {
                int nxt = e.to, w = e.cost;
                int new_cost = cost + w;
                if (dist[nxt] > new_cost) {
                    dist[nxt] = new_cost;
                    parent[nxt] = cur_node; // 경로 추적위해 기록
                    pq.offer(new int[]{dist[nxt],nxt});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // 최소비용 출력
        sb.append(dist[B]).append('\n');

        Deque<Integer> stack = new ArrayDeque<>();
        for(int cur = B; cur != -1; cur = parent[cur])
            stack.push(cur);
        // 길이 출력
        sb.append(stack.size()).append('\n');
        // 순서 출력
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(' ');

        System.out.println(sb.toString().trim());


    }
    static class Edge {
        int to, cost;
        Edge(int t, int c) {
            to = t;
            cost = c;
        }
    }
}
