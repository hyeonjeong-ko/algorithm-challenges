def check_position(place, i, j):
    # [검사 1] 맨해튼 거리 1: 상, 하, 좌, 우 4방향 검사
    for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
        ni, nj = i + dx, j + dy
        if 0 <= ni < 5 and 0 <= nj < 5:
            if place[ni][nj] == 'P':
                return False  # 인접한 칸에 응시자('P')가 있으면 위반

    # [검사 2] 맨해튼 거리 2 - 직선상의 경우
    for dx, dy in [(0, 2), (0, -2), (2, 0), (-2, 0)]:
        ni, nj = i + dx, j + dy
        if 0 <= ni < 5 and 0 <= nj < 5:
            if place[ni][nj] == 'P':
                # 두 칸 사이의 중간 좌표 계산
                mid_i, mid_j = i + dx // 2, j + dy // 2
                if place[mid_i][mid_j] != 'X':
                    return False  # 중간 칸이 파티션이 아니면 위반

    # [검사 3] 맨해튼 거리 2 - 대각선의 경우
    for dx, dy in [(-1, -1), (-1, 1), (1, -1), (1, 1)]:
        ni, nj = i + dx, j + dy
        if 0 <= ni < 5 and 0 <= nj < 5:
            if place[ni][nj] == 'P':
                # 대각선인 경우, (i, j+dy)와 (i+dx, j)가 모두 파티션('X')여야 함
                if not (place[i][j + dy] == 'X' and place[i + dx][j] == 'X'):
                    return False
    # 모든 검사를 통과하면 안전함
    return True

def check_place(place):
    """
    한 대기실(place)의 모든 좌표를 순회하여,
    응시자('P')가 있는 위치에 대해 check_position 함수를 호출합니다.
    
    하나라도 위반사항이 발견되면 0 (위반)으로 반환하고,
    모두 규칙을 준수하면 1 (안전)로 반환합니다.
    
    매개변수:
      place: 5x5 대기실 구조 (각 문자열은 한 행)
      
    반환:
      int: 1 (규칙 준수) 또는 0 (규칙 위반)
    """
    for i in range(5):
        for j in range(5):
            if place[i][j] == 'P':
                # 좌표 (i, j)에서 거리두기 규칙 위반이 있으면 즉시 0 반환
                if not check_position(place, i, j):
                    return 0
    return 1

def solution(places):
    answer = []
    
    # 각 대기실(place)에 대해 check_place 함수를 호출하여 결과를 저장
    for place in places:
        answer.append(check_place(place))
    
    return answer
