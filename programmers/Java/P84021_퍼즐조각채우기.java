// simul
/*
    BFS로 빈칸,조각블록 추출
    좌표 정규화: 각 좌표에서 x,y 최솟값 빼기
    pattern[coords]: cnt -> 대표 패턴 구해 조각개수 매칭
*/

import java.util.*;

public class P84021_퍼즐조각채우기 {
    // Directions: down, up, right, left
    private static final int[][] DIRS = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;

        // 1) 게임 보드의 빈칸 블록(0) 추출
        List<List<int[]>> emptyBlocks = extractBlocks(game_board, 0, n);

        // 2) 테이블의 퍼즐 조각 블록(1) 추출
        List<List<int[]>> pieceBlocks = extractBlocks(table, 1, n);

        // 3) 퍼즐 조각을 회전해 대표 패턴을 구해 카운트
        //    Map<패턴 문자열, 개수>
        Map<String, Integer> patterns = new HashMap<>();
        for (List<int[]> block : pieceBlocks) {
            String canon = canonicalForm(block);
            patterns.put(canon, patterns.getOrDefault(canon, 0) + 1);
        }

        // 4) 빈칸 블록마다 대표 패턴을 구해 매칭 시도
        int answer = 0;
        for (List<int[]> empty : emptyBlocks) {
            String canon = canonicalForm(empty);
            Integer count = patterns.getOrDefault(canon, 0);
            if (count > 0) {
                answer += empty.size();
                patterns.put(canon, count - 1);
            }
        }

        return answer;
    }

    // "target" 값(0 또는 1)에 대해 연결된 블록(컴포넌트)을
    // 정규화된 상대 좌표 리스트로 추출
    private List<List<int[]>> extractBlocks(int[][] board, int target, int n) {
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> blocks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // target에 해당하고 방문하지 않았다면 BFS 시작
                if (board[i][j] == target && !visited[i][j]) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    List<int[]> coords = new ArrayList<>();

                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});

                    // BFS로 블록 좌표 수집
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int x = cur[0], y = cur[1];
                        coords.add(new int[]{x, y});

                        for (int[] d : DIRS) {
                            int nx = x + d[0], ny = y + d[1];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n
                                    && !visited[nx][ny]
                                    && board[nx][ny] == target) {
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }

                    // 정규화: 절대좌표 → 상대좌표 (최소 x, 최소 y를 빼서 (0,0) 기준으로)
                    List<int[]> norm = normalize(coords);
                    blocks.add(norm);
                }
            }
        }

        return blocks;
    }

    // 블록 좌표 리스트를 받아, 최소 x, 최
    // 소 y를 빼서 (0,0) 기준으로 변환한 뒤 정렬하여 반환
    private List<int[]> normalize(List<int[]> coords) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] p : coords) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }

        List<int[]> normalized = new ArrayList<>();
        for (int[] p : coords) {
            normalized.add(new int[]{p[0] - minX, p[1] - minY});
        }

        // (x, y) 쌍을 x 오름차순, x 같으면 y 오름차순으로 정렬
        normalized.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        return normalized;
    }

    // 블록에 대해 네 방향 회전(시계 반대 90°)을 모두 시도하여
    // 정규화된 문자열 형태 중 사전순 최소값을 반환
    private String canonicalForm(List<int[]> block) {
        List<int[]> cur = new ArrayList<>(block);
        List<String> forms = new ArrayList<>();

        for (int rot = 0; rot < 4; rot++) {
            // 정규화된 문자열로 변환하여 forms에 추가
            forms.add(toKey(cur));
            // 다음 방향으로 회전
            cur = rotate(cur);
        }

        // 사전순으로 제일 작은 문자열을 대표 패턴으로 선택
        Collections.sort(forms);
        return forms.get(0);
    }

    // 주어진 블록을 시계 반대 90° 회전 → 정규화 → 정렬하여 반환
    // (x, y) → (y, -x), 이후 최소값을 빼서 (0,0) 기준으로 맞춤
    private List<int[]> rotate(List<int[]> block) {
        List<int[]> rotated = new ArrayList<>();

        // (x, y) -> (y, -x)
        for (int[] p : block) {
            int x = p[0], y = p[1];
            rotated.add(new int[]{ y, -x });
        }

        // 회전 후 정규화
        return normalize(rotated);
    }

    // 블록(정규화된 좌표 리스트)을 문자열로 변환
    // ex: [(0,0), (0,1), (1,0)] → "0,0;0,1;1,0;"
    private String toKey(List<int[]> normCoords) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : normCoords) {
            sb.append(p[0]).append(",").append(p[1]).append(";");
        }
        return sb.toString();
    }
}
