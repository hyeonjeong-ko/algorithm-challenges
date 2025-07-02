# 규칙찾기
# level: 3

def solution(s):
    answer = []
    
    for string in s:
        # 1. 110 모두 제거하기
        stack = []
        cnt = 0
        for c in string:
            stack.append(c)
            while len(stack) >= 3 and stack[-3:] == ['1','1','0']:
                stack.pop()
                stack.pop()
                stack.pop()
                cnt += 1

        
        # 2. 마지막0에 붙이기
        insert_idx = -1
        for i in range(len(stack)-1, -1, -1):
            if stack[i] == '0':
                insert_idx = i + 1
                break
        
        # 3. "110" 삽입
        insert_str = ['1','1','0'] * cnt
        if insert_idx == -1:
            # '0'이 없으면 맨 앞에 넣기
            new_string = insert_str + stack
        else:
            # '0'다음에 삽입
            new_string = stack[:insert_idx] + insert_str + stack[insert_idx:]
        
        answer.append(''.join(new_string))
        
    
    
    return answer
