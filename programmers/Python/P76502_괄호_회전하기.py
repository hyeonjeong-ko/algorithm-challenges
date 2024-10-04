from collections import deque

def is_valid(s):
    m = {')':'(', '}':'{', ']':'['}
    st = []
    for c in s:
        if c in m.keys():
            if len(st)==0:
                return False
            if st.pop() != m[c]:
                return False
        else: # 여는 괄호는 스택에 추가 ({[
            st.append(c)
    return len(st)==0

def solution(s):
    answer = 0
    
    # q rotate돌리면서 하나씩 확인
    
    q = deque(list(s))
    
    for i in range(len(s)):
        tmp_list = list(q)
        joined = ''.join(tmp_list)
        if is_valid(joined):
            answer+=1
        q.rotate(-1)
    
    return answer
