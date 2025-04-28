import re


def solution(new_id):
    # 1. 대->소문자 치환
    new_id = new_id.lower()

    # 2. 허용된 문자(a–z, 0–9, '-', '_', '.')를 제외한 모든 문자 제거
    new_id = re.sub(r"[^a-z0-9\-\_\.]", "", new_id)

    # 3. 마침표(.)가 2번이상 연속된 부분 하나 마침표로 치환
    new_id = re.sub(r"\.{2,}", ".", new_id)

    # 4. 문자열 맨앞or맨끝 마침표(.) 제거
    new_id = new_id.strip(".")

    # 5. 빈 문자열이면 "a" 대입
    if new_id == "":
        new_id = "a"

    # 6. 길이가 16자 이상이면 첫 15만 남기고 자르기
    #   잘라낸 후 끝에 마침표 있다면 다시 제거
    if len(new_id) >= 16:
        new_id = new_id[:15].rstrip(".")

    # 7. 길이 2자이하라면, 마지막 문자 반복해서 길이 3을 맞춤
    while len(new_id) < 3:
        new_id += new_id[-1]

    return new_id
