import sys
from collections import deque
input = sys.stdin.readline

N = int(input()) # 도시 수
M = int(input()) # 도로 수
A = [[] for _ in range(N + 1)]
reversedA = [[] for _ in range(N + 1)]
indegree = [0] * (N + 1) # 진입 차수 리스트

for i in range(M):
    S, E, V = map(int, input().split())
    A[S].append((E, V)) # 연결노드, 가중치
    reversedA[E].append((S,V)) # 역방향 에지 정보 저장
    indegree[E] += 1 # 진입 차수 저장

startDosi,endDosi = map(int, input().split())

q = deque() # 큐 초기화
q.append(startDosi)
result = [0] * (N + 1)

# 위상정렬
while q:
    now = q.popleft()
    for next in A[now]:
        indegree[next[0]] -= 1
        # 연결노드 임계경로값 vs 현재노드 임계 경로값 + 현재~연결노드 사이 경로값
        result[next[0]] = max(result[next[0]], result[now] + next[1])
        if indegree[next[0]] == 0:
            q.append(next[0])
            
resultCount = 0
visited = [False] * (N + 1)
q.clear()
q.append(endDosi)
visited[endDosi] = True

# 위상정렬 reverse 수행
while q:
    now = q.popleft()
    for next in reversedA[now]:
        # 1분도 쉬지 않는 도로 체크
        if result[next[0]] + next[1] == result[now]: # ex_ 6(next) <- 7[now]
            resultCount += 1 # 1분도 쉬지않는 도로수 + 1
            if not visited[next[0]]:
                visited[next[0]] = True
                q.append(next[0])
                
print(result[endDosi])
print(resultCount)
