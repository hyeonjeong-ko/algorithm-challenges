"""
구현 문제.

*문제 핵심 정리
명령어 처리 로직
- 각 명령어를 차례대로 수행하며 위치를 업데이트한다.

경계 조건 처리:
- 10초 이전 위치로 이동할 때는 0초로 설정.
- 동영상의 길이를 넘어서면 마지막 위치로 설정.
- 오프닝 구간에 있을 때 오프닝 끝 위치로 자동 이동.

시간 변환 함수
- "mm"를 초로 변환하는 함수와 초를 "mm" 형식으로 다시 변환하는 함수가 필요
"""

def solution(video_len, pos, op_start, op_end, commands):
    # 시간(str)->초(int)단위로 변환
    def to_seconds(time_str):
        minutes, seconds = map(int, time_str.split(":"))
        return minutes * 60 + seconds

    # 초(int)->"mm:ss" 형식 변환
    def time_to_str(seconds):
        minutes = seconds // 60
        seconds = seconds % 60
        return f"{minutes:02}:{seconds:02}"

    # 모두 초단위로 변환
    video_len_sec = to_seconds(video_len)
    pos_sec = to_seconds(pos)
    op_start_sec = to_seconds(op_start)
    op_end_sec = to_seconds(op_end)

    for cmd in commands:
        # 명령어 처리 후 바로 오프닝 구간 체크 및 이동
        if op_start_sec <= pos_sec <= op_end_sec:
            pos_sec = op_end_sec
            
        if cmd == "prev":
            pos_sec = max(0, pos_sec - 10)
        elif cmd == "next":
            pos_sec = min(video_len_sec, pos_sec + 10)

        # 명령어 처리 후 바로 오프닝 구간 체크 및 이동
        if op_start_sec <= pos_sec <= op_end_sec:
            pos_sec = op_end_sec

    return time_to_str(pos_sec)
