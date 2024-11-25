def solution(record):
    answer = []
    m = {}  # user_id : name

    # 1. record에서 정보를 처리
    for line in record:
        cmd = line.split(" ")
        if cmd[0] != "Leave":  # "Enter"와 "Change"일 경우에만 닉네임 업데이트
            m[cmd[1]] = cmd[2]

    # 2. 다시 record를 순차적으로 처리하여 출력 메시지 생성
    for info in record:
        cmd = info.split(" ")
        if cmd[0] == "Enter":
            answer.append(f"{m[cmd[1]]}님이 들어왔습니다.")
        elif cmd[0] == "Leave":
            answer.append(f"{m[cmd[1]]}님이 나갔습니다.")

    return answer
