# recursion, simul

import sys

input = sys.stdin.readline
N = int(input())
paper = [list(map(int, input().split())) for _ in range(N)]

white = blue = 0


def is_same_color(x, y, size):
    color = paper[x][y]
    for i in range(x, x + size):
        for j in range(y, y + size):
            if paper[i][j] != color:
                return False
    return True


def count_paper(x, y, size):
    global white, blue
    color = paper[x][y]
    if is_same_color(x, y, size):
        if paper[x][y] == 0:
            white += 1
        else:
            blue += 1
    else:
        half = size // 2
        count_paper(x, y, half)
        count_paper(x, y + half, half)
        count_paper(x + half, y, half)
        count_paper(x + half, y + half, half)


count_paper(0, 0, N)

print(white)
print(blue)
