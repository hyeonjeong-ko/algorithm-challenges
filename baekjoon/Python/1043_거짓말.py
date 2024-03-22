N, M = map(int, input().split())
trueP = list(map(int, input().split())) # 진실 아는 사람
T = trueP[0] # 진실을 아는 사람 수
del trueP[0]
result = 0
party = [[] for _ in range(M)]
parent = [i for i in range(0, N + 1)]

def find(a):
    if parent[a] == a:
        return a
    else:
        parent[a] = find(parent[a])
        return parent[a]
        
def union(a, b):
    a = find(a)
    b = find(b)
    
    if a!=b:
        parent[b] = a
        
def checkSame(a, b): # 대표 노드 같은지 확인
    a = find(a)
    b = find(b)
    if a == b:
        return True
    return False

for i in range(M):
    party[i] = list(map(int,input().split())) # 파티 데이터 저장
    del party[i][0]

# 각 파티에 참여한 사람을 한개의 그룹으로 만들기
for i in range(M):
    first = party[i][0]
    for j in range(1, len(party[i])):
        union(first,party[i][j])

# 각 파티와 진실 아는 사람들중 한명이라도 같은 그룹이면 (대표노드가 같으면) 과장할 수 없다.
for i in range(M):
    isPossible = True
    firstPeople = party[i][0]
    for j in range(len(trueP)):
        if find(firstPeople) == find(trueP[j]):
            isPossible = False
            break
    if isPossible:
        result += 1 # 모드 다르면 결괏값 증가

print(result)
