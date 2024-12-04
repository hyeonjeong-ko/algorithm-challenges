# 조합 & 해시맵
import itertools
from collections import Counter


def solution(orders, course):
    result = []

    # 각 course 길이에 대해 반복
    for k in course:
        candidates = []

        # 각 주문에서 길이 k의 조합을 추출
        for order in orders:
            # 알파벳 순으로 정렬된 상태에서 조합 생성
            candidates += itertools.combinations(sorted(order), k)

        # 조합의 등장 횟수 세기
        count = Counter(candidates)

        # 등장한 조합 중에서 최소 2번 이상 등장한 조합 찾기
        if count:
            max_count = max(count.values())
            if max_count >= 2:
                # 가장 많이 등장한 조합을 결과에 추가
                for combo in count:
                    if count[combo] == max_count:
                        result.append("".join(combo))

    # 결과를 사전 순으로 정렬하여 반환
    return sorted(result)
