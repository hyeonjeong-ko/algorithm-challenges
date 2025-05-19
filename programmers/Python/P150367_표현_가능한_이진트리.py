# 포화이진트리
# Todo 1. 십진수 이진수로 바꾸기
# Todo 2. 완전 포화 이진트리의 노드 수 만큼 왼쪽에 0 붙여주기 (완전포화:2^n-1 크기로 증가) zfill()
# Todo 3. 이진탐색으로 트리로 표현 가능한지 확인

def solution(numbers):
    def to_full_binary_tree(binary_str):
        N = len(binary_str)
        height = 0
        while (2 ** height - 1) < N:
            height+=1
        full_len = 2 ** height - 1
        return binary_str.zfill(full_len) # str.zfill(len)
    
    def is_valid(binary_str):
        if len(binary_str) == 1:
            return True
        mid = len(binary_str) // 2
        root = binary_str[mid]
        left = binary_str[:mid]
        right = binary_str[mid+1:]
        
        # 부모가 더미노드인데,자식중 실제노드있으면 잘못된 트리
        if root == '0':
            if left[mid//2] == '1' or right[mid//2] == '1':
                return False
        return is_valid(left) and is_valid(right)
    
    answer = []
    for num in numbers:
        binary_str = bin(num)[2:]
        padded_str = to_full_binary_tree(binary_str)
        result = 1 if is_valid(padded_str) else 0
        answer.append(result)
    return answer
