"""
@brute-force, simulation
가능한 모든 8x8 영역을 순회하면서 두 가지 패턴(W로 시작, B로 시작)을 체크.
짝수, 홀수 위치의 색상을 기준으로 칠하기 횟수를 계산하고, 두 패턴 중 더 적은 칠하기 횟수를 선택.  
"""

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1018 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] board = new char[N][M];
		for (int i = 0; i < N; i++) { // 보드 입력받기
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				board[i][j] = temp[j];
			}
		}

		int min = Integer.MAX_VALUE;
		for (int x = 0; x <= N - 8; x++) { // 체스판 시작점 x,y 탐색
			for (int y = 0; y <= M - 8; y++) {
				int wCnt = 0;
				for (int i = x; i < x + 8; i++) {
					for (int j=y; j < y + 8; j++) {
						if((i+j)%2==1 && board[i][j]=='W') wCnt++;
						else if ((i+j)%2==0 && board[i][j]=='B') wCnt++;
					}
				}
				min = Math.min(min, Math.min(wCnt,64-wCnt));
			}
		}
		System.out.println(min);
	}
}
