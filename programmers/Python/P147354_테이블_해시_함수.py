"""
주어진 테이블의 데이터를 정렬하고 XOR 연산을 통해 해시 값을 계산하는 문제.

메타데이터:
- **난이도**: 중간
- **소요 시간**: 20min

분석:

구현:
- **알고리즘**:
  - 간단 구현 문제
  - `col` 열을 기준으로 오름차순 정렬, 값이 같다면 첫 번째 열을 기준으로 내림차순 정렬.
  - `[row_begin, row_end]` 범위의 각 행에 대해, 해당 행 번호로 나눈 나머지 값을 더한 결과를 계산.
  - 이 값을 XOR 연산하여 최종 해시 값을 구함.
"""


def solution(data, col, row_begin, row_end):
    answer = 0
    
    # col-1 idx기준 오름차순정렬, 값 동일하면 첫번째 칼럼기준 내림차순 정렬
    data = sorted(data,key= lambda x: (x[col-1],-x[0]))
    
    answer += 0
    for i in range(row_begin - 1, row_end):
        s_i = sum([val % (i + 1) for val in data[i]])
        answer ^= s_i
    
    return answer
