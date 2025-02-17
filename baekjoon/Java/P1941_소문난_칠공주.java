// backtracking

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 소문난_칠공주 {
	static int ans = 0;
	static char[][] arr = new char[5][5];
	static int[][] v = new int[5][5];
	// 상하좌우 이동을 위한 방향 배열
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			arr[i] = line.toCharArray();
		}
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	// 선택된 7칸이 상하좌우로 연결되어 있는지 확인하는 함수
	static boolean check() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (v[i][j] == 1) {
					// 선택된 칸 중 하나에서 시작하여 BFS로 연결 여부 확인
					return bfs(i, j);
				}
			}
		}
		return false;
	}

	private static void dfs(int n, int cnt, int scnt) {
		if (cnt > 7) return; // 이미 7명을 초과하면 가지치기
		if (n == 25) {
			// 7명을 선택했고, 이다솜파('S') 학생이 4명 이상인 경우
			if (cnt == 7 && scnt >= 4) { // 모두 인접하면 +1
				if (check()) ans++;
			}
			return;
		}

		int r = n / 5;
		int c = n % 5;

		v[r][c] = 1; // 현재 칸을 포함하는 경우
		dfs(n + 1, cnt + 1, scnt + (arr[r][c] == 'S' ? 1 : 0));
		v[r][c] = 0; // 원상 복구

		dfs(n + 1, cnt, scnt); // 현재 칸을 포함하지 않는 경우
	}

	// BFS를 통해 선택된 칸들이 모두 인접한지 체크
	static boolean bfs(int si, int sj) {
		boolean[][] visited = new boolean[5][5];

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{si,sj});
		visited[si][sj] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int ci = cur[0], cj = cur[1];
			for (int d = 0; d < 4; d++) {
				int ni = ci + dx[d];
				int nj = cj + dy[d];
				// 범위내, 미방문, 조합에 포함된 수이면 방문
				if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5) {
					if (!visited[ni][nj] && v[ni][nj] == 1) {
						visited[ni][nj] = true;
						cnt++;
						q.add(new int[]{ni, nj});
					}
				}
			}
		}
		return cnt == 7; // 선택된 칸 모두 방문했는지 확인
	}
}
