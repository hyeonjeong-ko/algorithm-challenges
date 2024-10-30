def solution(N, stages):
    giveup = {}
    total = len(stages)
    
    levels = [0] * (N + 2)  # N+1까지 수용하기 위해 N+2로 설정
    
    # 각 레벨별 인원 계산
    for stage in stages:
        if stage <= N:
            levels[stage] += 1
    
    # 실패율 계산
    for i in range(1, N + 1):
        if total > 0:
            fail_rate = levels[i] / total
            giveup[i] = fail_rate
            total -= levels[i]
        else:
            giveup[i] = 0  # 스테이지에 도달한 유저가 없는 경우 실패율을 0으로 설정
    
    # 실패율 내림차순 및 스테이지 번호 오름차순 정렬
    ans = sorted(giveup, key=lambda x: (-giveup[x], x))
    
    return ans
