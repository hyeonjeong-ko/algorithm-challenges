from heapq import heappush, heappop
"""
메타데이터
난이도: 중급
소요 시간: 1시간
정답률: Medium

분석
입력: 
  - 예약된 대실 시간을 나타내는 리스트 (형식: [["시작시간", "종료시간"], ...])
기대 시간 복잡도: O(N)

구현
자료구조: min Heap
알고리즘: 힙(Heap)을 이용한 시뮬레이션

결과
시간 복잡도: O(N log N)
"""

def solution(book_time):
    book_time.sort()
    ends = []
    for start, end in book_time:
        if ends and ends[0] <= 60 * int(start[:2]) + int(start[3:]):
            heappop(ends)
        heappush(ends, 60 * int(end[:2]) + int(end[3:]) + 10)
    return len(ends)


"""
pointer 이용 풀이

def solution(book_time):
    starts = sorted(60 * int(s[:2]) + int(s[3:]) for s, _ in book_time)
    ends = sorted(60 * int(e[:2]) + int(e[3:]) for _, e in book_time)

    max_cnt, cnt = 0, 0
    s, e = 0, 0
    while s < len(starts):
        if ends[e] + 10 <= starts[s]:
            cnt, e = cnt - 1, e + 1
        else:
            cnt, s = cnt + 1, s + 1
            max_cnt = max(cnt, max_cnt)
    return max_cnt
"""
