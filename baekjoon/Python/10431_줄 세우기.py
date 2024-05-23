# https://www.acmicpc.net/problem/10431
T = int(input())
for tc in range(1,T+1):
    lst = list(map(int,input().split()))[1:]
    
    cnt = 0
    for i in range(1,20):
        for j in range(i):
            if lst[i] < lst[j]:
                cnt += 1
    print(f'{tc} {cnt}')
