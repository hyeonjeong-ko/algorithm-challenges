import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N = int(input())

def isPrime(num):
    for i in range(2, int(num / 2 + 1)):
        if num % i == 0:
            return False
    return True

def dfs(number):
    if len(str(number)) == N:
        print(number)
    else:
        for i in range(1, 10): # 2,3,5,7 소수 탐색
            if i % 2 == 0: # 짝수 탐색X
                continue
            if isPrime(number * 10 + i): # 자릿수 늘리기
                dfs(number * 10 + i)
dfs(2)
dfs(3)
dfs(5)
dfs(7)
