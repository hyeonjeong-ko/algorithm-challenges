/*
- **Difficulty**: Hard
- **Time Taken**: 5 Hr
- **Answer Rate**: 24.896%

**Analysis**
**Input**:
- Three integers: N (number of cities), M (number of roads), K (number of interview places)
- M lines of integers U, V, C representing a road from city U to city V with length C
- A line of K integers representing the cities with interview places

**Output**:
- Two integers: the city with the maximum shortest distance to an interview place and the distance itself

**Implementation**
- **Data Structures**:
  - `ArrayList<Edge>[] adj`: An adjacency list to store the graph where adj[i] contains all edges going to city i
  - `int[] dist`: An array to store the shortest distance from any interview place to each city
  - `boolean[] visited`: An array to track visited cities during Dijkstra's algorithm
  - `PriorityQueue<Edge> q`: A priority queue to implement the Dijkstra's algorithm
  - `class Edge`: A helper class to store the destination city and the distance

- **Algorithms**:
  - **Dijkstra's Algorithm**:
    - Initialize distances to infinity, and set the distance of interview places to 0
    - Use a priority queue to repeatedly process the nearest unvisited city
    - For each city, update the distances to its neighbors
    - Keep track of the farthest city from any interview place

  - **Steps**:
    1. Parse input and initialize data structures
    2. Build the graph with reversed edges
    3. Set up the priority queue with all interview places
    4. Perform Dijkstra's algorithm to compute shortest distances
    5. Find the city with the maximum shortest distance to an interview place
    6. Output the result
    
  - **Comments**:
  처음 생각; 각 정점들에 대해 다익스트라를 여러번 돌려 면정장들까지의 거리를 하나하나 구해주는 것. -> 시간초과가 난다. O(K(N + M) log N)
  깨달음과 해결; 역방향 그래프를 만들어, 면접장에서 각지점 까지 가는 최단 거리를 찾는다. "다익스트라 알고리즘의 시작점은 여러개여도 된다!"
  다익스트라 알고리즘은 현재까지 최단 거리가 보장된 정점들의 정보를 기반으로, 최단 거리가 가장 작은 정점을 선택하여 확장합니다. 이 원리에 따라 여러 시작점을 설정해도 올바르게 작동한다.
  추가로, long타입을 써야 오버플로우가 나지 않음에 주의하자.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static ArrayList<Edge>[] adj;
    static long[] dist;
    static boolean[] visited;
    static PriorityQueue<Edge> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new long[N + 1];
        visited = new boolean[N + 1];
        adj = new ArrayList[N + 1]; // 초기화 주의! (빼먹지 말자)

        for (int i = 0; i <= N; i++) {
            dist[i] = Long.MAX_VALUE; // long 타입으로 변경
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) { // 인접 리스트 생성
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adj[E].add(new Edge(S, C)); // 역방향 간선 추가
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) { // 면접장 장소 입력받기
            int place = Integer.parseInt(st.nextToken());
            q.add(new Edge(place, 0)); // 큐에 삽입
            dist[place] = 0;
        }

        while (!q.isEmpty()) {
            Edge now = q.poll();
            int now_val = now.val;
            if (visited[now_val]) continue;
            visited[now_val] = true;

            for (Edge tmp : adj[now_val]) {
                int next = tmp.val;
                long weight = tmp.dist;
                if (dist[now_val] + weight < dist[next]) { // 최소 거리로 업데이트
                    dist[next] = dist[now_val] + weight;
                    q.add(new Edge(next, dist[next])); // 최소 거리 갱신 후 큐에 추가
                }
            }
        }

        // 최대 거리 찾기
        long maxDist = -1;
        int maxCity = -1;
        for (int i = 1; i <= N; i++) {
            if (dist[i] > maxDist && dist[i] != Long.MAX_VALUE) {
                maxDist = dist[i];
                maxCity = i;
            } else if (dist[i] == maxDist && i < maxCity) {
                maxCity = i;
            }
        }
        System.out.println(maxCity);
        System.out.println(maxDist);
    }
}

class Edge implements Comparable<Edge> {
    int val;
    long dist;

    public Edge(int val, long dist) {
        this.val = val;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge edge) {
        return Long.compare(this.dist, edge.dist);
    }
}
