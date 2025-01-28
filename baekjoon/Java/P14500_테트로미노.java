import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 테트로미노 {
	// 테트로미노 기본 모양
	static int[][][] minos = {
		{{0, 0}, {0, 1}, {0, 2}, {0, 3}},  // I자
		{{0, 0}, {0, 1}, {1, 0}, {1, 1}},  // O자
		{{0, 0}, {1, 0}, {2, 0}, {2, 1}},  // L자
		{{0, 0}, {1, 0}, {1, 1}, {2, 1}},  // Z자
		{{0, 0}, {0, 1}, {0, 2}, {1, 1}}   // T자
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 90도 회전과 상하 대칭된 모든 보드 생성 (총 8개)
		List<int[][]> boards = new ArrayList<>();
		boards.add(board); // 원본 보드
		int[][] rotated = board;
		for (int i = 0; i < 3; i++) { // 90도씩 3번 회전
			rotated = rotate(board);
			boards.add(rotated);
		}
		for (int i = 0; i < 4; i++) { // 각 회전된 보드의 대칭 추가
			boards.add(mirror(boards.get(i)));
		}

		// 각 보드 상태에서 최대값 계산
		int answer = 0;
		for (int[][] b : boards)
			answer = Math.max(answer, getMaxSum(b));

		System.out.println(answer);
	}

	// 보드에서 모든 테트로미노 배치의 최대 합 계산
	private static int getMaxSum(int[][] board) {
		int maxSum = 0;
		int R = board.length;
		int C = board[0].length;
		// 모든 좌표(시작점)에서 5개 테크로미노 배치 중 최대값 계산
		for (int offsetX = 0; offsetX < R; offsetX++) {
			for (int offsetY = 0; offsetY < C; offsetY++) {
				for (int[][] mino : minos) {
					Integer currentSum = getSingleSum(board, mino, offsetX, offsetY);
					if (currentSum != null)
						maxSum = Math.max(maxSum, currentSum);
				}
			}
		}
		return maxSum;
	}

	// 테트로미노 한 모양의 합 계산
	private static Integer getSingleSum(int[][] board, int[][] mino, int offsetX, int offsetY) {
		int R = board.length;
		int C = board[0].length;
		int totalSum = 0;
		for (int[] point : mino) {
			int x = point[0] + offsetX;
			int y = point[1] + offsetY;
			if (x < 0 || x >= R || y < 0 || y >= C) {
				return null; // 범위를 벗어나면 null 반환
			}
			totalSum += board[x][y];
		}
		return totalSum;
	}

	// 보드를 90도 회전시키는 함수
	public static int[][] rotate(int[][] board) {
		int R = board.length;
		int C = board[0].length;
		int[][] newBoard = new int[C][R];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				newBoard[c][R - 1 - r] = board[r][c];
			}
		}
		return newBoard;
	}

	// 보드를 좌우 대칭시키는 함수
	public static int[][] mirror(int[][] board) {
		int R = board.length;
		int C = board[0].length;
		int[][] newBoard = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				newBoard[r][C - 1 - c] = board[r][c];
			}
		}
		return newBoard;
	}
}
