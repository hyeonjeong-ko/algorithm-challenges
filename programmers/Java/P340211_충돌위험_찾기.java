// simul, bfs

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 충돌위험_찾기 {
	static int answer = 0;
	static List<String> pathLog = new ArrayList<>(); // 초단위 최단경로
	static Map<String, Integer> counter = new HashMap<>(); // 충돌위험

	public int solution(int[][] points, int[][] routes) {

		for (int[] route : routes) {
			pathLog.addAll(bfs(points, route));
		}


		// 충돌 위험 계산
		for (String key : pathLog)
			counter.put(key, counter.getOrDefault(key,0) + 1);

		// 2번 이상 방문된 위치의 개수 카운트
		for (int count : counter.values()){
			if (count>=2) answer++;
		}
		return answer;
	}

	// 각 로봇의 경로 계산
	private List<String> bfs(int[][] points, int[] route) {
		int sec = 0;
		ArrayList<String> path = new ArrayList<>();

		// 각 포인트 사이 최단 경로 계산
		for (int i = 0; i < route.length - 1; i++) {
			int[] startPoint = points[route[i] - 1];
			int[] endPoint = points[route[i + 1] - 1];

			int sx = startPoint[0], sy = startPoint[1];
			int ex = endPoint[0], ey = endPoint[1];

			// x 좌표 맞추기
			while (sx != ex) {
				path.add(sx + "," + sy + "," + sec); // 현재 위치와 시간 기록
				sx = (sx < ex) ? sx + 1 : sx - 1;
				sec++;
			}

			// y 좌표 맞추기
			while (sy != ey) {
				path.add(sx + "," + sy + "," + sec); // 현재 위치와 시간 기록
				sy = (sy < ey) ? sy + 1 : sy - 1;
				sec++;
			}
		}
		// 최종 도착지점 기록
		int[] lastPoint = points[route[route.length-1] - 1];
		path.add(lastPoint[0] + "," + lastPoint[1] + "," + sec);
		return path;
	}

	// main 함수 - 테스트 실행
	public static void main(String[] args) {
		충돌위험_찾기 finder = new 충돌위험_찾기();

		int[][] points = {
			{3, 2}, {6, 4}, {4, 7}, {1, 4}
		};

		int[][] routes = {
			{4, 2}, {1, 3}, {2, 4}
		};

		int result = finder.solution(points, routes);
		System.out.println(result); // 결과 출력: 1
	}

}
