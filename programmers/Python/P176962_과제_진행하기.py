def solution(plans):
    answer = []
    tmp = []
    idx=0
    for name, start, playtime in plans:
        hours, minutes = map(int, start.split(":"))
        plans[idx][1] = hours * 60 + minutes
        plans[idx][2] = int(playtime)
        idx+=1

    plans = sorted(plans,key = lambda x: x[1])

    for i in range(0, len(plans) - 1):
        current_end_time = plans[i][1] + plans[i][2]
        next_start_time = plans[i+1][1]

        if current_end_time > next_start_time:
            # 작업 보류
            remaining_time = current_end_time - next_start_time
            tmp.append([plans[i][0], remaining_time])
        else:
            # 현재 과제 완료
            answer.append(plans[i][0])
            # 남은 시간 동안 멈춘 과제 처리
            gap = next_start_time - current_end_time
            while tmp and gap > 0:
                name, remaining_time = tmp.pop()
                if remaining_time <= gap: # 남은시간이 gap이하면 작업완료가능
                    gap -= remaining_time
                    answer.append(name)
                else: # gap보다크면 일부만 진행
                    tmp.append([name, remaining_time - gap])
                    break

    # 마지막 과제 처리
    answer.append(plans[-1][0])
    # 남은 멈춘 과제 처리
    while tmp:
        name, remaining_time = tmp.pop()
        answer.append(name)

    return answer
 

자바풀이는 아래와같다

import java.util.*;

class Solution {
