"""
스택 활용: 아직 가격이 떨어지지 않은 인덱스를 스택에 저장.
기간 확정: 현재 가격이 떨어질 때, 스택에 쌓인 이전 가격들의 유지 기간을 확정.
남은 가격 처리: 끝까지 떨어지지 않은 가격들은 리스트 끝까지의 거리로 유지 기간 계산.
"""
def solution(prices):
    N = len(prices)
    answer = [0] * N
    st = []  # 기간이 확정되지 않은 인덱스 저장
    
    for i in range(N):
        # 스택의 인덱스가 가리키는 가격이 현재 가격보다 클 때까지 팝
        # 확정가능한 가격 모두 확정짓기
        while st and prices[i] < prices[st[-1]]:
            idx = st.pop()
            answer[idx] = i - idx  # 유지된 기간 계산
        st.append(i)
    
    # 끝까지 가격이 유지된 경우 계산
    while st:
        idx = st.pop()
        answer[idx] = N - 1 - idx

    return answer
