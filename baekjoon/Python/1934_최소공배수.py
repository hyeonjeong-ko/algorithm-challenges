t = int(input())


def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b) # 작은수, mod연산


for i in range(t):
    a, b = map(int, input().split())
    result = a * b // gcd(a, b)
    print(result)
