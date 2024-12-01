N = int(input())  # 스위치 개수
switches = [0] + list(map(int, input().split()))  # 1-based 인덱스
student_cnt = int(input())  # 학생 수

for _ in range(student_cnt):
    student_type, number = map(int, input().split())

    if student_type == 1:
        # 남학생
        for i in range(number, len(switches), number):
            switches[i] = 1 - switches[i]

    elif student_type == 2:
        # 여학생
        idx = 0
        # 범위 내에서 대칭 구간 찾기
        while (
            number - idx >= 1
            and number + idx <= N
            and switches[number - idx] == switches[number + idx]  # 대칭 조건 확인
        ):
            idx += 1

        # idx가 1 증가한 후 멈추므로 실제 대칭 범위는 idx - 1
        idx -= 1

        # 대칭 범위의 모든 스위치 상태를 반전
        for i in range(number - idx, number + idx + 1):
            switches[i] = 1 - switches[i]

# 최종 스위치 상태를 20개씩 출력
for i in range(1, len(switches), 20):
    print(" ".join(map(str, switches[i : i + 20])))
