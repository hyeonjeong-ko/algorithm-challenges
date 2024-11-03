"""
@Binary Search

문제요구:
- get(key, timestamp): 주어진 타임스탬프 이하의 가장 최근 값을 반환.
핵심 구현:
- 각 키에 대해 (timestamp, value) 리스트 저장, get에서 이진 탐색으로 최근 값 빠르게 찾기. O(N)->O(NlogN)
"""
from collections import defaultdict
from bisect import bisect_right

class TimeMap:

    def __init__(self):
        self.keyStore = defaultdict(list) # key: list of [timestamp,val]

    def set(self, key: str, value: str, timestamp: int) -> None:
        # 각 키에 대해 (타임스탬프, 값) 쌍을 추가
        self.keyStore[key].append((timestamp, value))

    def get(self, key: str, timestamp: int) -> str:
        if key not in self.keyStore:
            return ""
        
        # 타임스탬프 리스트만 추출해서 이진 탐색
        timestamps = self.keyStore[key]
        
        # bisect_right로 주어진 타임스탬프보다 작거나 같은 가장 큰 인덱스를 찾기
        i = bisect_right(timestamps, (timestamp, chr(127))) - 1
        
        # 인덱스가 유효한지 확인 (유효하지 않으면 "" 반환)
        if i >= 0:
            return timestamps[i][1]
        return ""

"""
   def get(self, key: str, timestamp: int) -> str:
        # 해당 키가 없으면 "" 반환
        if key not in self.keyStore:
            return ""
        
        # 타임스탬프 리스트만 추출해서 이진 탐색을 수행
        timestamps = self.keyStore[key]
        
        res = ""
        l,r = 0,len(timestamps)-1
        while l<=r:
            m = (l + r)//2
            if timestamps[m][0] <= timestamp:
                res = timestamps[m][1]
                l = m + 1
            else:
                r = m - 1
        return res
"""
