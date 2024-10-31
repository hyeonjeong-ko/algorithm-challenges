def solution(n, left, right):
    answer = []
    
    for idx in range(left,right+1):
        row = idx // n
        col = idx % n
        answer.append(max(row,col) + 1)
    
    return answer
