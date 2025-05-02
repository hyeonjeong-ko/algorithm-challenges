def solution(park, routes):
    answer = []
    H, W = len(park), len(park[0])

    for i in range(H):  # 시작점 찾기
        for j in range(W):
            if park[i][j] == "S":
                x, y = i, j

    direcitons = {"E": (0, 1), "W": (0, -1), "N": (-1, 0), "S": (1, 0)}

    for route in routes:
        op, dist = route.split()
        dx, dy = direcitons[op]
        dist = int(dist)

        nx, ny = x, y
        success = True

        for step in range(1, dist + 1):
            tx = x + dx * step
            ty = y + dy * step

            if 0 <= tx < H and 0 <= ty < W and park[tx][ty] != "X":
                continue
            else:
                success = False
                break

        if success:
            x += dx * dist
            y += dy * dist

    return [x, y]
