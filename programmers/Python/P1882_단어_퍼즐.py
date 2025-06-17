def solution(strs, t):
    INF = float("inf")
    n = len(t)
    
    dp = [INF] * (n + 1)
    dp[0] = 0
    
    for i in range(1, n + 1):
        for w in strs:
            if i >= len(w) and t[i-len(w):i] == w:
                dp[i] = min(dp[i],dp[i - len(w)] + 1)
    

    return dp[n] if dp[n] != INF else -1
