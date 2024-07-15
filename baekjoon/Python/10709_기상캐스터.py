H, W = map(int, input().split())
arr = [input() for _ in range(H)]
v = [[0]*W for _ in range(H)]

for i in range(H):
    cnt = -1 # 'c' 만나지 못하면 계속 -1 기록!
    for j in range(W):
        if arr[i][j] == 'c': # 구름 만나면 0으로 초기화, 증가 시작!
            cnt = 0
        else: # 구름을 만났다면 증가
            if cnt >= 0:
                cnt+=1
        v[i][j] = cnt
    
for lst in v:
    print(*lst)
