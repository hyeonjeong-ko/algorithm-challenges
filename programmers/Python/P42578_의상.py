# hash,dic

from collections import defaultdict

def solution(clothes):
    answer = 1
    
    dic = defaultdict(int)
    
    for cloth, _type in clothes:
        dic[_type] += 1
        
    for cnt in dic.values():
        answer *= (cnt + 1) # 악세서리안입는경우도 포함
    
    return answer - 1 # 아무것도아예안입는경우제거
