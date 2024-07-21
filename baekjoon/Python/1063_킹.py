"""
A solution to a chess piece movement simulation.

Metadata
- **Difficulty**: Medium
- **Time Taken**: 45 mins

Analysis
**Input**:
- Three strings from user input: `K`, `S`, and `N`
  - `K`: Current position of the King on a chessboard (e.g., "A1")
  - `S`: Current position of a Stone on a chessboard (e.g., "B2")
  - `N`: Number of moves to process

**Expected Time Complexity**: O(N)

**Implementation**
- **Data Structures**: 
  - Dictionary (HashMap) for direction mappings
- **Algorithms**: 
  - Mapping character-based chess positions to integer indices

**Result**
- **Time Complexity**: O(N)

More
- **Key insights**: 
  - Convert chess board positions to numerical indices for easier manipulation. (ord(),chr())
"""

def toPos(st):  # ex_ A1 -> i,j
    return int(st[1]), ord(st[0]) - ord("A") + 1


def toAB(i, j):  # ex_ i,j -> A1
    return chr((j - 1) + ord("A")) + str(i)


K, S, N = input().split()

ci, cj = toPos(K)  # 킹 위치
si, sj = toPos(S)  # 돌 위치
N = int(N)
dct = {
    "R": (0, 1),
    "L": (0, -1),
    "B": (-1, 0),
    "T": (1, 0),
    "RT": (1, 1),
    "LT": (1, -1),
    "RB": (-1, 1),
    "LB": (-1, -1),
}
for _ in range(N):
    di, dj = dct[input()]
    ni, nj = ci + di, cj + dj
    if 1 <= ni <= 8 and 1 <= nj <= 8:  # 체스 범위내
        if (ni, nj) == (si, sj):  # 이동 위치에 돌 있는 경우
            ei, ej = si + di, sj + dj  # 돌이 이동할 위치
            if 1 <= ei <= 8 and 1 <= ej <= 8:  # 돌 체스 범위내
                si, sj = ei, ej  # 돌위치 변경
                ci, cj = ni, nj  # 킹위치 변경
        else:
            ci, cj = ni, nj

print(toAB(ci, cj), toAB(si, sj), sep="\n")
