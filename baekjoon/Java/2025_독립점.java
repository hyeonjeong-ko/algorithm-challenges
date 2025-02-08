import java.io.*;
import java.util.*;


public class 독립점 {
	public static void main(String[] args) throws IOException {
		// 빠른 입출력을 위해 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 정점 수
		int m = Integer.parseInt(st.nextToken()); // 간선 수

		// 인접 리스트 생성 (정점 번호 1~n 사용)
		ArrayList<Integer>[] graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		// 간선 입력 받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		boolean[] visited = new boolean[n + 1];
		int answer = 0;

		// 모든 정점을 순회하며 아직 방문하지 않은 컴포넌트를 찾는다.
		for (int i = 1; i <= n; i++) { // 해당 그룹 탐색
			if (!visited[i]) {
				// BFS를 이용하여 컴포넌트 내의 정점 수와 간선 수(각 간선은 2번 세어짐)를 구한다.
				int vertexCount = 0; // 정점 수
				int sumDegrees = 0; // 간선 수

				Queue<Integer> queue = new ArrayDeque<>();
				queue.add(i);
				visited[i] = true;

				while (!queue.isEmpty()) {
					int cur = queue.poll();
					vertexCount++;
					sumDegrees += graph[cur].size();

					for (int next : graph[cur]) {
						if (!visited[next]) {
							visited[next] = true;
							queue.add(next);
						}
					}
				}

				// 실제 간선 수 (각 간선은 양쪽에서 카운트되었으므로 2로 나눈다)
				int edgeCount = sumDegrees / 2;

				// 컴포넌트가 1개 정점인 경우 (고립 정점)
				if (vertexCount == 1) {
					answer += 1;
				}
				// 사이클인 경우: 간선 수 == 정점 수 (단, vertexCount > 1)
				else if (edgeCount == vertexCount) {
					answer += vertexCount / 2;  // floor(vertexCount/2)
				}
				// 그 외는 경로인 경우: 간선 수 == vertexCount - 1
				else {
					answer += (vertexCount + 1) / 2;  // ceil(vertexCount/2)
				}
			}
		}

		System.out.println(answer);
	}
}
