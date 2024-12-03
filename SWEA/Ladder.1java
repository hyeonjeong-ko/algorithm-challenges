
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ladder1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10; // 테스트 케이스 개수

		for (int test_case = 1; test_case <= T ; test_case++) {
			br.readLine(); // 필요없으므로 입력무시
			int N = 100; // 100 x 100
			int[][] arr = new int[N][N + 2]; // 양옆 패딩 추가

			// [1] 배열 입력 받기
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 1; j <=N; j++) {
					arr[i][j] = Integer.parseInt(line[j - 1]);
				}
			}

			// [2] 마지막 행에서 출발 위치 찾기
			int ci = N - 1; // 마지막 행
			int cj = 0; // 출발 열

			for (int j = 1; j <= N; j++) {
				if (arr[ci][j] == 2) {
					cj = j;
					break;
				}
			}

			// [3] 사다리 역으로 올라가기
			while (ci > 0) {
				arr[ci][cj] = 0; // 중복 방문 방지
				if (arr[ci][cj - 1] == 1) {cj--;} // 왼
				else if (arr[ci][cj + 1] == 1) {cj++;} // 오
				else{ci--;} // 위로 이동
			}

			// [4] 결과 저장
			sb.append("#").append(test_case).append(" ").append(cj - 1).append("\n");
		}
		System.out.println(sb);

	}
}
