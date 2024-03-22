from collections import deque
N, E = map(int, input().split())
A = [[] for _ in range(N + 1)] # 비교 데이터 저장 인접 리스트
indegree = [0] * (N + 1) # 진입 차수 리스트

# 인접 리스트 데이터 저장
for i in range(E):
    S, E = map(int, input().split())
    A[S].append(E)
    indegree[E]+=1 # 진입차수 데이터 저장

queue = deque()
for i in range(1, N + 1): # 진입 차수 값 0인 노드 큐에 삽입
    if indegree[i] == 0:
        queue.append(i)
        
while queue: # 위상 정렬
    now = queue.popleft()
    print(now, end = ' ')
    # 현재노드 연결노드
    for next in A[now]:
        indegree[next] -= 1 # 연결노드 진입차수 감소
        if indegree[next] == 0:
            queue.append(next)
