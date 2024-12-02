# simul, bfs

from collections import Counter


def solution(points, routes):
    def bfs(route):
        idx = 0
        pa = []
        for i in range(len(route) - 1):
            sx, sy = points[route[i] - 1]
            ex, ey = points[route[i + 1] - 1]

            # x 좌표 맞추기
            while sx != ex:
                pa.append((sx, sy, idx))
                if sx < ex:
                    sx += 1
                else:
                    sx -= 1
                idx += 1

            # y 좌표 맞추기
            while sy != ey:
                pa.append((sx, sy, idx))
                if sy < ey:
                    sy += 1
                else:
                    sy -= 1
                idx += 1
        pa.append((sx, sy, idx))
        return pa

    second = []

    for route in routes:
        second.extend(bfs(route))
    res = 0
    temp = Counter(second)
    for i in temp.values():
        if i >= 2:
            res += 1

    return res


# 예시 입력
points = [[3, 2], [6, 4], [4, 7], [1, 4]]
routes = [[4, 2], [1, 3], [2, 4]]
print(solution(points, routes))  # 출력: 1

"""
points = [[3, 2], [6, 4], [4, 7], [1, 4]]
routes = [[4, 2], [1, 3], [4, 2], [4, 3]]
print(solution(points, routes))  # 출력: 9

points = [[2, 2], [2, 3], [2, 7], [6, 6], [5, 2]]
routes = [[2, 3, 4, 5], [1, 3, 4, 5]]
print(solution(points, routes))  # 출력: 0
"""
