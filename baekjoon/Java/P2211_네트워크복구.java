

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* algo: dijkstra
* */
// comparable<Node>, compareTo(Node o2)

public class P2211_네트워크복구 {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o2) {
            return Integer.compare(this.cost, o2.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 컴퓨터 수
        int M = Integer.parseInt(st.nextToken()); // 회선 수

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C)); // 양방향
        }

        // 다익스트라 초기화
        int[] dist = new int[N + 1];
        int[] parent = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.to;
            int cost = cur.cost;

            if (cost > dist[now]) {
                continue;
            }

            for (Node next : graph.get(now)) {
                int nextNode = next.to;
                int weight = next.cost;

                if (dist[nextNode] > dist[now] + weight) {
                    dist[nextNode] = dist[now] + weight;
                    parent[nextNode] = now;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        // 복구 회선 출력
        List<int[]> res = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            res.add(new int[]{parent[i], i});
        }
        System.out.println(res.size());
        for (int[] line : res) {
            System.out.println(line[0] + " " + line[1]);
        }
    }
}
