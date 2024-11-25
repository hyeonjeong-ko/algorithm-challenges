# Hash,Sort

from collections import defaultdict

def solution(genres, plays):
    answer = []
    m = defaultdict(list)
    
    # 장르별 노래 정보 저장
    for i in range(len(genres)):
        m[genres[i]].append((plays[i], i))
    
    # 장르별 총 재생 횟수 계산
    sum_per_key = {key: sum(value[0] for value in values) for key, values in m.items()}
    
    # 총 재생 횟수 기준으로 장르 정렬
    sorted_sum_per_key = sorted(sum_per_key.items(), key=lambda x: x[1], reverse=True)

    # 베스트 앨범 구성
    for genre, _ in sorted_sum_per_key:
        # 장르 내에서 재생 횟수와 고유 번호를 기준으로 정렬
        songs = sorted(m[genre], key=lambda x: (-x[0], x[1]))
        
        # 상위 2곡 추가
        answer.extend([song[1] for song in songs[:2]])
    
    return answer
