# simul, data structure

def solution(friends, gifts):
    # 1) 친구 이름 → 인덱스 매핑
    idx = {name: i for i, name in enumerate(friends)}
    n = len(friends)

    # 2) 데이터 구조 초기화
    #    gave[i][j]: i번 친구가 j번 친구에게 준 선물 횟수
    gave = [[0] * n for _ in range(n)]
    #    given[i]: i번 친구가 준 선물 총합
    given = [0] * n
    #    received[i]: i번 친구가 받은 선물 총합
    received = [0] * n

    # 3) gifts 기록 채우기
    for record in gifts:
        giver, receiver = record.split()
        u, v = idx[giver], idx[receiver]
        gave[u][v] += 1
        given[u] += 1
        received[v] += 1

    # 4) 선물 지수(net) 계산: 준 총합 − 받은 총합
    net = [given[i] - received[i] for i in range(n)]

    # 5) 다음 달 받을 선물 개수 계산
    recv_next = [0] * n
    for i in range(n):
        for j in range(i + 1, n):
            if gave[i][j] != gave[j][i]:
                # 직접 주고받은 횟수가 다르면, 더 많이 준 사람이 받음
                if gave[i][j] > gave[j][i]:
                    recv_next[i] += 1
                else:
                    recv_next[j] += 1
            else:
                # 주고받은 횟수가 같거나 전혀 없으면
                if net[i] != net[j]:
                    # 선물 지수가 더 큰 사람이 받음
                    if net[i] > net[j]:
                        recv_next[i] += 1
                    else:
                        recv_next[j] += 1
                # 지수도 같으면 아무도 못 받음

    # 6) 가장 많이 받을 친구가 받을 개수 리턴
    return max(recv_next)
