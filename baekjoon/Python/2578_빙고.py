"""
A solution to the bingo game problem.

Metadata
Difficulty: Intermediate
Time Taken: 1.5 hr
Correct Answer Rate: 45.724%

Analysis
Input:
- 5x5 grid for bingo numbers
- List of called numbers by the host
Expected Time Complexity: O(N), where N is the number of called numbers

Implementation
Data Structures: Arrays, Lists
Algorithms: Counting, Array Indexing
Statements: for loop, if conditions, break statements

Result
Time Complexity: O(N)

More
- Key insights: Using a position list to quickly find coordinates of called numbers; Checking bingo conditions efficiently with arrays for rows, columns, and diagonals.
"""
arr = [list(map(int, input().split())) for _ in range(5)]

lst = [] # 사회자가 부르는 숫자 (1차원)
for _ in range(5):
    lst += list(map(int, input().split()))
    
# 번호마다 좌표 위치 저장
pos_lst = [0]*26
for i in range(5):
    for j in range(5):
        pos_lst[arr[i][j]] = (i, j)

v = [[0]*10 for _ in range(4)] #v0~v3 빈도수 체크
# 사회자가 부르는 좌표를 읽어, 빈도수 체크, 5인 개수가 3개 이상이면 종료
for n in lst:
    i, j = pos_lst[n] # 부른 번호의 좌표
    v[0][j] += 1 # 세로 개수 누적
    v[1][i] += 1 # 가로 개수 누적
    v[2][i-j]+=1 # 우측 아래 대각선 개수 누적
    v[3][i+j]+=1 # 우측 위쪽 대각선 개수 누적
    cnt = 0
    for tlst in v:
        cnt += tlst.count(5) # 5개 개수
    if cnt>=3:
        break
print(sum(v[0]))

"""
처음 풀었던 풀이

def check_bingo(board):
    check_three = 0
    for row in board: # 행 빙고 체크
        if all(row) == True:
            check_three+=1
    trans =[list(row) for row in zip(*board)]
    for col in trans: # 열 빙고 체크
        if all(col) == True:
            check_three+=1
    # 대각선빙고-1
    for i in range(5):
        if board[i][i] == 0:
            break
        elif i==4:
            check_three+=1
    # 대각선빙고-2
    for i in range(5):
        if board[i][4-i] == 0:
            break
        elif i==4:
            check_three+=1
        
    if check_three >= 3:
        return True


board = []
call = []
check = [[0] * 5 for _ in range(5)]
cnt = 0

for _ in range(5): # 유저 보드
    row = list(map(int,input().split()))
    board.append(row)

for _ in range(5): # 사회자가 부르는 번호
    row = list(map(int,input().split()))
    call.append(row)

flag = False
for row in call: # 사회자가 부르는 숫자 하나씩 체크에 표시
    for n in row:
        cnt+=1
        # board에서 해당숫자 위치좌표를 찾기
        for i, row in enumerate(board):
            if n in row:
                check[i][row.index(n)] = cnt
                # 체크 빙고 함수 호출
                if check_bingo(check):
                    print(cnt)
                    flag = True
                    break
        if flag:
            break
    if flag:
        break
"""
