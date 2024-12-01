# 구현

d = dict()

for _ in range(int(input())):
    book = input()
    if book in d:
        d[book] += 1
    else:
        d[book] = 1


m = max(d.values())  # 베스트 판매량
candi = []
for k, v in d.items():
    if v == m:
        candi.append(k)

# candi.sort()
# print(candi[0])
print(sorted(candi)[0]) # 사전순
