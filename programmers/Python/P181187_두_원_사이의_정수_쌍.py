"""
**핵심 아이디어:**

- **대칭성 활용**: 원은 대칭적이므로 한 사분면의 점들만 계산하고 전체 점의 개수를 구할 때 4를 곱
- **정수 좌표 개수 산출**: `math.floor`와 `math.ceil` 함수를 사용
- **정수 y 값의 개수 계산**:
  - 가능한 y 값의 개수는 `floor(max_r2) - ceil(max_r1) + 1`
  - `+1`은 양 끝 값도 포함하기 위해 필요
"""
import math

def solution(r1, r2):
    answer = 0  
    
    # 가능한 x,y쌍 구하기
    for x in range(1, r2+1):
        max_r2 = math.sqrt(r2**2 - x**2)
        max_r1 = 0 if x > r1 else math.sqrt(r1**2 - x**2)
        
        # 큰원, 작은원 사이 y 개수
        answer += math.floor(max_r2) - math.ceil(max_r1) + 1
          
    return answer * 4
