// hashMap 응용
from collections import defaultdict


def solution(id_list, report, k):
    reported = defaultdict(set)  # 신고당한사람 : 신고자
    for r in report:
        user_id, reported_id = r.split()
        reported[reported_id].add(user_id)

    report_cnt = defaultdict(int)  # 유저:신고횟수
    for reported_id, users in reported.items():
        # 정지 기준 만족할때 메일발송
        if len(users) >= k:
            for uid in users:
                report_cnt[uid] += 1

    answer = []
    for i in range(len(id_list)):
        if id_list[i] not in report_cnt:
            answer.append(0)
        else:
            answer.append(report_cnt[id_list[i]])
    return answer
