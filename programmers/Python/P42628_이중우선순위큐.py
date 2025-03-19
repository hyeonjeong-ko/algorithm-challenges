# heapq

import heapq

def solution(operations):
    answer = []
    
    min_heap = []
    max_heap = []
    
    hash_set = set() # 현재 존재하는 값들
    
    for operation in operations:
        op = operation.split()
        if op[0] == "I":
            num = int(op[1])
            heapq.heappush(min_heap, num)
            heapq.heappush(max_heap, -num)
            hash_set.add(num)
        elif op[0] == "D":
            if op[1] == "1":
                while max_heap and -max_heap[0] not in hash_set:
                    heapq.heappop(max_heap)
                if max_heap:
                    max_val = -heapq.heappop(max_heap)
                    hash_set.remove(max_val)
            elif op[1] == "-1":
                # min_heap은 중간에 있는값 제거할수 없음
                # 삭제된 값(유효x값)이 나오면 건너뛰어야함
                # "유효한 최솟값" 찾을때까지 반복
                while min_heap and min_heap[0] not in hash_set:
                    heapq.heappop(min_heap)
                if min_heap:
                    min_val = heapq.heappop(min_heap)
                    hash_set.remove(min_val)
    
    if not min_heap or not max_heap:
        return [0, 0] # 큐 비어있으면 0,0 반환
    
    # 최댓값과 최솟값 반환
    while min_heap and min_heap[0] not in hash_set:
        heapq.heappop(min_heap)
    while max_heap and -max_heap[0] not in hash_set:
        heapq.heappop(max_heap)
    return [-max_heap[0], min_heap[0]]
    
                
    
    return answer
