import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ladder2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10; // 테스트 케이스 개수
		int N = 100; // 100 x 100 크기

		for (int test_case = 1; test_case <= T; test_case++) {
			br.readLine(); // 테스트 케이스 번호 입력(소비)
			int[][] arr = new int[N][N + 2]; // 양옆 패딩 추가

			// [1] 사다리 입력받기
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Integer.parseInt(line[j - 1]);
				}
			}

			int mn = N * N; // 최단 거리 초기화
			int ans = 0; // 최단 거리 출발점

			// [2] 모든 출발점에서 시뮬레이션
			for (int sj = 1; sj <= N; sj++) {
				if (arr[0][sj] == 0) continue; // 출발점이 0인 경우 스킵

				int cj = sj; // 현재 열
				int cnt = 0; // 이동 거리
				int dr = 0; // 이동 방향 (0: 아래, -1: 왼쪽, 1: 오른쪽)
				int ci = 0; // 현재 행

				while (ci < N - 1) { // 바닥에 도달할 때까지
					cnt++;
					if (dr == 0) { // 아래로 이동 (이동 + 다음탐색방향 설정)ㅌ
						ci++;
						if (arr[ci][cj - 1] == 1) { // 왼쪽으로 이동
							dr = -1;
						} else if (arr[ci][cj + 1] == 1) { // 오른쪽으로 이동
							dr = 1;
						}
					} else { // 좌우로 이동
						cj += dr;
						if (arr[ci][cj + dr] == 0) { // 더 이상 이동 불가 시
							dr = 0; // 아래로 전환
						}
					}
				}

				// [3] 최단 거리 갱신
				if (cnt <= mn) {
					mn = cnt;
					ans = sj - 1; // 패딩 고려
				}
			}

			// [4] 결과 출력
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
}
