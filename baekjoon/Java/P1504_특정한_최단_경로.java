/**
 * 문제: 특정한 최단 경로
 * 
 * 알고리즘 유형: 다익스트라(Dijkstra) 알고리즘
 * 
 * 시간 복잡도:
 * - 다익스트라 알고리즘: O((E + V) log V)
 * - 여기서 E는 간선의 수, V는 정점의 수
 * 
 * 깨달은 점:
 * - 최단 경로 문제에서 INF(무한대) 값을 설정할 때, 너무 큰 값(Integer.MAX_VALUE)을 사용하면 path를 구하기 위해 길이를 더할때 오버플로우가 발생하여 잘못된 결과를 초래할 수 있다.
 * - 문제의 제약 조건을 고려하여 안전한 INF 값을 설정해야 한다. 이 문제에서는 최대 가능한 경로 길이보다 큰 200,000,000을 INF로 설정하여 오버플로우를 방지했다.
 * - 다익스트라 알고리즘을 여러 번 사용하여 특정 정점을 거치는 최단 경로를 효과적으로 계산할 수 있다.
 */
public class P_1504 {
    static int N, M;
    static ArrayList<Edge>[] edges;
    static int[] distance;
    static boolean[] visited;
    static int INF = 200_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<Edge>(); //인접 리스트 초기화
        }
        for (int i = 0; i < M; i++) { //가중치 인접리스트 초기화
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[s].add(new Edge(e, v));
            edges[e].add(new Edge(s, v)); //양방향
        }
        //# Step 1: 각 다익스트라 실행
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int dist_1_to_v1 = dijkstra(1, v1);
        int dist_v1_to_v2 = dijkstra(v1, v2);
        int dist_v2_to_N = dijkstra(v2, N);

        int dist_1_to_v2 = dijkstra(1, v2);
        int dist_v1_to_N = dijkstra(v1, N);


        //# Step 2: 두 가지 경로의 최단 거리 계산
        int path1 = dist_1_to_v1 + dist_v1_to_v2 + dist_v2_to_N;
        int path2 = dist_1_to_v2 + dist_v1_to_v2 + dist_v1_to_N;

        //# Step 3: 최단 거리 선택
        int ans = path1>=INF && path2>=INF ? -1 : Math.min(path1, path2);
        System.out.println(ans);

    }

    public static int dijkstra(int startNode, int endNode) {
        visited = new boolean[N + 1];
        distance = new int[N + 1];
        Arrays.fill(distance, INF); // distance 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.offer(new Edge(startNode, 0));
        distance[startNode] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            for (int i = 0; i < edges[cur.vertex].size(); i++) {
                Edge next = edges[cur.vertex].get(i);

                if(distance[next.vertex] > distance[cur.vertex] + next.weight) {
                    distance[next.vertex] = distance[cur.vertex] + next.weight;
                    pq.add(new Edge(next.vertex, distance[next.vertex]));
                }
            }
        }
        return distance[endNode];
    }
}

class Edge implements Comparable<Edge> {
    int vertex, weight;

    Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) return 1;
        else return -1;
    }
}
