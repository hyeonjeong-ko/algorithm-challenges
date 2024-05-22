import sys

input = sys.stdin.readline
t = int(input())
for i in range(t):
    tmp = input().rstrip()
    left = []
    right = []

    for a in tmp:
        if a == '<':
            if left:
                right.append(left.pop())
        elif a == '>':
            if right:
                left.append(right.pop())
        elif a == '-':
            if left:
                left.pop()
        else:
            left.append(a)

    # 최종 결과 출력
    answer = left + right[::-1]
    print(''.join(answer))
