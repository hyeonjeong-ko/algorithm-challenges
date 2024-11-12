"""
시간초과 주의.딕셔너리 사용해 시간초과 개선.
O(n)->O(1)
"""
def solution(players, callings):
    player_indices = {player:idx for idx,player in enumerate(players)}
    
    for name in callings:
        idx = player_indices[name] # 추월
        
        prev = players[idx-1]
        
        # 리스트에서 위치 변경
        players[idx], players[idx - 1] = players[idx - 1], players[idx]
        
        # 딕셔너리 업데이트
        player_indices[name] = idx-1
        player_indices[prev] = idx
    
    return players
