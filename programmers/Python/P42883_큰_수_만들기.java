"""
그리디: 큰 숫자를 앞자리에 배치해 가장 큰 숫자 만들기
단조 감조 스택: 스택 숫자가 내림차순을 유지하도록 작은 숫자 제거
시간 복잡도: O(n)
"""


def solution(number, k):
    stack = []
    for digit in number:
        while stack and stack[-1] < digit and k > 0:
            stack.pop()  # 해당 숫자 제거
            k -= 1
            stack.append(digit)

    # k가 남아 있다면 뒤에서 k개를 제거
    if k > 0:
        stack = stack[:-k]

    return "".join(stack)
