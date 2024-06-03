import sys

input = sys.stdin.readline

N = int(input())
arr = [int(input()) for i in range(N)]
st = []
ans = []
num = 1
res = True

for i in range(N):
    if arr[i] >= num:  # 주어진수열값 >= 자연수
        # 숫자같아질때까지 스택쌓기
        while arr[i] >= num:
            st.append(num)
            num += 1
            ans.append("+")
        st.pop()  # 값이 같아지면 pop()
        ans.append("-")

    else:  # 주어진 수열값 < 자연수
        n = st.pop()
        if n != arr[i]:
            print("NO")
            res = False
            break
        else:
            ans.append("-")
if res:
    for op in ans:
        print(op)
