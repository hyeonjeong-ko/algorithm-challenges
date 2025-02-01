def hanoi(n, start, end, via):
    """
    하노이 탑 재귀 함수
    n: 옮길 원판의 수
    start: 시작 기둥 번호
    end: 목표 기둥 번호
    via: 보조(경유) 기둥 번호
    """
    if n == 1:
        print(start, end)
    else:
        # 1) N-1개 원판을 start -> via 로 옮기기 (end를 보조로 사용)
        hanoi(n-1, start, via, end)
        
        # 2) 가장 큰 원판( n번 )을 start -> end 로 이동
        print(start, end)
        
        # 3) N-1개 원판을 via -> end 로 옮기기 (start를 보조로 사용)
        hanoi(n-1, via, end, start)


def solve():
    import sys
    input = sys.stdin.readline

    N = int(input())
    
    # 하노이 탑 최소 이동 횟수 (2^N - 1)
    K = 2**N - 1
    print(K)
    
    # N이 20 이하일 때만 이동 과정 출력
    if N <= 20:
        hanoi(N, 1, 3, 2)

 
solve()
