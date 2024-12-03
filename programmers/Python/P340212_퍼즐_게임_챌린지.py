# binary search

def solution(diffs, times, limit):
    answer = 0
    N = len(diffs)
    
    def time_by_level(k):
        idx = 0
        time = 0
        while idx < N:
            if diffs[idx] <= k:
                time+=times[idx]
            else:
                wrong = diffs[idx] - k
                time+=(times[idx] + times[idx-1]) * wrong + times[idx]
            idx+=1
        return time
    
    levels = [i for i in range(1, 100_000 + 1)]
    
    l = 1 # 숙련도 최소값
    r = max(diffs) # 숙련도 최대값
    ans = 0
    
    while l <= r:
        m = (l + r) // 2
        taken_time = time_by_level(m)
        
        if taken_time > limit:
            l = m + 1
        else:
            ans = m  # 조건을 만족할 때 정답 후보 갱신
            r = m - 1
    return ans
