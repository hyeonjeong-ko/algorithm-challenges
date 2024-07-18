N = int(input())
# 최소개수 => 5킬로 봉투를 최대한 사용, 3킬로만 사용할 수도 있으니
# N//5 ~ 0까지 루프
for cnt_5 in range(N // 5, -1, -1):
    cnt_3 = (N - 5 * cnt_5) // 3  # 5킬로 봉투량 뺀 무게를 3킬로 봉지수로 계산

    if cnt_5 * 5 + cnt_3 * 3 == N:
        print(cnt_5 + cnt_3)
        break
else:
    print(-1)
