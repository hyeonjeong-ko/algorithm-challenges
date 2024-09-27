"""
브루트포스는 시간초과, 각 원소를 한번씩만처리하는 것이 관건.
뒷 큰수를 아직 찾지못한 원소 추적해야 함.
최근 원소와(가장 가까운 원소) 비교 -> LIFO -> Stack

point:
- 뒷 큰수를 찾지못한 원소 인덱스를 저장해, 더큰수 만나면 한번에 업데이트
- 단조 감소 스택(Monotonic Decreasing Stack): 스택에 저장된 인덱스들은 원소 값이 스택 아래로 갈수록 커짐. (값 감소순서로 인덱스가 쌓임)
"""
def solution(numbers):
    answer = [-1] * len(numbers)
    st = []
    
    for idx, val in enumerate(numbers):
        # 스택 비어있지 않고 끝 값이 현재값보다 작을때 
        while st and numbers[st[-1]] < val:
            prev_idx = st.pop()
            answer[prev_idx] = val
        st.append(idx) # 현재 인덱스 스택에 추가
    
    return answer
