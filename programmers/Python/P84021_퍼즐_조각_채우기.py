"""
algo: simul
level: medium~hard

BFS로 빈칸,조각블록 추출
좌표 정규화: 각 좌표에서 x,y 최솟값 빼기
pattern[coords]: cnt -> 대표 패턴 구해 조각개수 매칭
"""


from collections import deque, defaultdict


def solution(game_board, table):
    n = len(game_board)
    # 상하좌우 이동
    dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    # target 값(0 또는 1)에 대해 연결된 블록(컴포넌트)을
    # 정규화된 상대 좌표 리스트로 추출
    def extract_blocks(board, target):
        visited = [[False] * n for _ in range(n)]
        blocks = []

        for i in range(n):
            for j in range(n):
                if board[i][j] == target and not visited[i][j]:
                    q = deque([(i, j)])
                    visited[i][j] = True
                    coords = []

                    # BFS로 블록 좌표 수집
                    while q:
                        x, y = q.popleft()
                        coords.append((x, y))
                        for dx, dy in dirs:
                            nx, ny = x + dx, y + dy
                            if (
                                0 <= nx < n
                                and 0 <= ny < n
                                and not visited[nx][ny]
                                and board[nx][ny] == target
                            ):
                                visited[nx][ny] = True
                                q.append((nx, ny))

                    # 정규화: 최소 x,y 빼서 (0,0) 기준으로 변환
                    xs = [x for x, y in coords]
                    ys = [y for x, y in coords]
                    min_x, min_y = min(xs), min(ys)
                    norm = sorted((x - min_x, y - min_y) for x, y in coords)
                    blocks.append(norm)

        return blocks

    # 상대 좌표 블록을 시계반대 90° 회전 후 정규화하여 반환
    def rotate(block):
        # (x,y) -> (y,-x)
        rotated = [(y, -x) for x, y in block]
        xs = [x for x, y in rotated]
        ys = [y for x, y in rotated]
        min_x, min_y = min(xs), min(ys)
        # 다시 (0,0) 기준으로
        return sorted((x - min_x, y - min_y) for x, y in rotated)

    # 1) 게임 보드의 빈칸 블록 (0)
    empty_blocks = extract_blocks(game_board, 0)
    # 2) 테이블의 퍼즐 조각 블록 (1)
    piece_blocks = extract_blocks(table, 1)

    # 3) 퍼즐 조각을 회전해 “대표 패턴” 하나만 뽑아 카운트
    patterns = defaultdict(int)
    for blk in piece_blocks:
        rots = []
        cur = blk
        # 네 방향 모두 저장
        for _ in range(4):
            rots.append(tuple(cur))
            cur = rotate(cur)
        # 사전식 최소 패턴 하나를 대표로 +1
        canon = min(rots)
        patterns[canon] += 1

    # 4) 빈칸 블록마다 대표 패턴을 만들어 매칭 시도
    answer = 0
    for eb in empty_blocks:
        # eb 자체도 네 방향 중 사전식 최소를 구해주면
        rots = []
        cur = eb
        for _ in range(4):
            rots.append(tuple(cur))
            cur = rotate(cur)
        canon = min(rots)

        # 매칭 가능하면 채운 칸 수 더하고 사용 처리
        if patterns[canon] > 0:
            answer += len(eb)
            patterns[canon] -= 1

    return answer
