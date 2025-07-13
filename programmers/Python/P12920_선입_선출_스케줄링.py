# level: 3
# algo: binary-search

def solution(n, cores):
    if n <= len(cores):
        return n

    low_time = 0
    high_time = max(cores) * n

    while low_time < high_time:
        mid = (low_time + high_time) // 2
        allocated = sum(mid // c for c in cores) + len(cores)
        if allocated >= n:
            high_time = mid
        else:
            low_time = mid + 1

    completed = sum((low_time - 1) // c for c in cores) + len(cores)
    for i, c in enumerate(cores):
        if low_time % c == 0:
            completed += 1
            if completed == n:
                return i + 1
    return -1
