from collections import deque

n, w, L = map(int, input().split())
li = deque(list(map(int, input().split())))

time = cur_weight = 0
bridge = deque([0] * w)

while li or cur_weight > 0:
    time += 1

    # 1) 다리 맨앞 한칸전진
    out = bridge.popleft()
    cur_weight -= out

    # 2) 다음 트럭 진입 가능 여부 판단 (0 or 트럭)
    if li:
        if cur_weight + li[0] <= L:
            truck = li.popleft()
            bridge.append(truck)
            cur_weight += truck
        else:
            bridge.append(0)  # 자리는 유지하되 트럭은 못들어옴

print(time)
