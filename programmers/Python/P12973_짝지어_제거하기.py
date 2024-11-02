# stack
def solution(s):
    st = []
    for c in s:
        if st and st[-1] == c: # 스택이 비지않고 현재문자와 맨위 문자가 같을때
            st.pop()
        else:
            st.append(c) # 스택에 현재 문자 추가

    return int(not st)
