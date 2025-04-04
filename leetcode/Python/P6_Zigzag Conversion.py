# simulation, easy

class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1 or len(s) <= numRows:
            return s

        rows = ['' for row in range(numRows)]

        curr_row = 0
        going_down = False

        for c in s:
            rows[curr_row] += c
            if curr_row == 0 or curr_row == numRows-1:
                going_down = not going_down
            
            curr_row += 1 if going_down else -1
        
        return ''.join(rows)
