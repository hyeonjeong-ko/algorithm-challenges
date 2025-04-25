# 전처리: info의 각 지원자 정보를 16가지 “조건 조합” 키로 미리 매핑
#    – 속성 4개마다 실제값 또는 '-' 선택 → 2⁴=16개의 키 생성
#    – db[key] 리스트에 support.score 추가

# 이분탐색(bisect_left):
#    idx = bisect_left(db[key], cut)
#    answer = len(db[key]) - idx
#    – 정렬된 점수 리스트에서 “cut 이상”인 첫 위치를 찾아
#      이후 요소 개수를 한 번에 계산


from collections import defaultdict
from bisect import bisect_left


def solution(info, query):
    answer = []
    db = defaultdict(list)

    # 1. info 전처리
    for inf in info:
        lang, job, career, food, score_str = inf.split()
        score = int(score_str)
        attrs = [lang, job, career, food]

        for mask in range(1 << 4):  # 나올수있는 모든 경우수들
            key_parts = []
            # 각 mask에 대해
            for i in range(4):  # 각 자리수(특성) 1인지 확인후 마스킹or정보처리
                if mask & (1 << i):
                    key_parts.append(attrs[i])
                else:
                    key_parts.append("-")
            key = " ".join(key_parts)
            db[key].append(score)

    # 2. 각 키별 점수 리스트 정렬
    for key in db:
        db[key].sort()

    # 3. 쿼리 처리
    answer = []
    for q in query:
        q = q.replace(" and ", " ")
        *conds, cut_str = q.split()
        cut = int(cut_str)
        key = " ".join(conds)
        # 조회
        if key in db:
            scores = db[key]
            # scores[idx] >= cut 인 첫 인덱스 찾기
            idx = bisect_left(scores, cut)  # 값이 들어갈 가장 왼쪽위치찾기
            answer.append(len(scores) - idx)
        else:
            answer.append(0)
    return answer
