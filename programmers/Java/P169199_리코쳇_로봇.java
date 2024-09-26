/*
리코쳇 로봇 문제 풀이

분석
입력:
- 보드는 행(rows) x 열(cols) 크기의 격자
- 'R'은 시작 위치, 'G'는 목표 지점, 'D'는 장애물, '.'은 빈 공간
- 로봇은 상, 하, 좌, 우로 이동하며, 벽이나 장애물에 부딪힐 때까지 미끄러짐
예상 시간 복잡도: O(행 * 열)

구현
자료 구조:
- 2D 배열(`map`): 보드 상태 저장
- 2D 배열(`visited`): 방문 여부 기록
- 큐(BFS): 탐색 경로와 이동 횟수 관리

알고리즘:
- BFS 사용: 시작 지점에서부터 네 방향으로 미끄러져 이동
- 각 방향에서 벽이나 장애물에 부딪힐 때까지 이동하며, 방문한 적 없는 위치만 탐색

결과
시간 복잡도: O(행 * 열)
- 각 위치에서 네 방향으로 탐색하며, 모든 칸을 한 번씩만 방문

추가
- 핵심: BFS는 가장 먼저 도달하는 경로가 최단 경로임을 보장
- `visited` 배열로 중복 탐색을 방지하여 효율성 유지
*/
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char[][] map;
    private int rows, cols;

    public int solution(String[] board) {
        rows = board.length;
        cols = board[0].length();  // 여기서 열의 길이를 첫 번째 문자열에서 가져옴
        map = new char[rows][cols];

        // String[]을 char[][]로 변환
        for (int i = 0; i < rows; i++) {
            map[i] = board[i].toCharArray();
        }

        int[] start = null;
        int[] goal = null;

        // 출발지점, 목표지점 찾기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 'R') {  // map을 사용하여 보드 정보를 확인
                    start = new int[]{i, j, 0}; // x, y, 이동횟수
                } else if (map[i][j] == 'G') {
                    goal = new int[]{i, j}; // 목표지점
                }
            }
        }

        return bfs(start, goal);
    }

    private int bfs(int[] start, int[] goal) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        q.offer(start);
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int moveCount = curr[2];

            // 종료 조건; 목표 지점에 도달
            if (x == goal[0] && y == goal[1]) {
                return moveCount;
            }

            // 네 방향으로 미끄러지며 이동
            for (int[] dir : DIRECTIONS) {
                int nx = x;
                int ny = y;

                // 장애물('D')이나 벽에 부딪힐 때까지 이동
                while (canMove(nx + dir[0], ny + dir[1])) {
                    nx += dir[0];
                    ny += dir[1];
                }

                // 방문하지 않은 위치라면 큐에 추가
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, moveCount + 1});
                }
            }
        }
        return -1; // 목표지점에 도달할 수 없는 경우
    }

    private boolean canMove(int x, int y) { // 범위 내, 장애물 체크
        return x >= 0 && x < rows && y >= 0 && y < cols && map[x][y] != 'D';
    }
}
