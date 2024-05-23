from collections import deque

N, K = map(int, input().split())
lst = [i for i in range(1, N + 1)]
q = deque(lst)
answer = []

while q:
    q.rotate(-(K-1))
    answer.append(q.popleft())  # K번째 사람을 제거하고 답에 추가
    
ans2 = "<" + ", ".join(map(str, answer)) + ">"
print(ans2)
