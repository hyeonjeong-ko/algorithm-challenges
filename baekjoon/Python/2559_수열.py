N, K = map(int, input().split())

lst = list(map(int, input().split()))

ans = sm = sum(lst[:K])
for i in range(K, N):
    # 바로 뒷자리를 추가, 제일 앞을 제거해서 연산 최소화
    sm = sm + lst[i] - lst[i - K]
    ans = max(ans, sm)
print(ans)
