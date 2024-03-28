from bisect import bisect_left

class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m, n = len(matrix), len(matrix[0])
        
        # 각 행의 첫 번째 요소들
        eachrow_first = [row[0] for row in matrix]
        
        # target이 위치할 행을 찾기
        target_row = bisect_left(eachrow_first, target)

        # 만약 행 첫번째위치에 답이 있다면
        if 0<=target_row<m and matrix[target_row][0] == target:
            return True
        else:
            # 찾은 행이 행렬 범위를 벗어날 경우, False
            target_row-=1 # 행인덱스
            if target_row<0 or m<=target_row:
                return False
            
            # 찾은 행에서 target을 이진 탐색
            ans = bisect_left(matrix[target_row], target)
            
            return 0 <= ans < n and matrix[target_row][ans] == target
