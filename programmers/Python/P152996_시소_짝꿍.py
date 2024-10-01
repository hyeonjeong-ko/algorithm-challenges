from collections import Counter

def solution(weights):
    answer = 0
    count = Counter(weights)
    for k, v in count.items():
        if v > 1:
            answer += v * (v - 1) // 2
    
    weights = list(set(weights))
    for item in weights:
        for check in (3/4, 2/3, 2/4):
            if item * check in weights:
                answer += count[item] * count[item * check]
                
    return answer
