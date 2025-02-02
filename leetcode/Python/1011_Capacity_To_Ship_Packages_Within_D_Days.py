class Solution:
    def shipWithinDays(self, weights: List[int], days: int) -> int:
        # 배 용량을 설정할 수 있는지 확인하는 함수
        def canShip(capacity: int) -> bool:
            days_needed = 1  # 처음에는 첫 번째 날부터 시작
            current_weight = 0  # 첫 날 배에 짐을 실기 위한 변수
            for weight in weights:
                if current_weight + weight > capacity:  # 현재 짐을 실을 수 없으면
                    days_needed += 1  # 하루를 더 추가
                    current_weight = 0  # 새로 배에 짐을 실기 시작
                current_weight += weight  # 배에 짐을 실음
            return days_needed <= days  # 주어진 날수 내에 짐을 실을 수 있는지 확인

        left, right = max(weights), sum(weights)  # 배의 용량 범위 설정
        while left < right:
            mid = (left + right) // 2  # 중간값 계산
            if canShip(mid):  # 가능한 용량을 찾으면 더 작은 용량을 찾음
                right = mid
            else:  # 불가능하면 더 큰 용량을 찾음
                left = mid + 1
        return left  # 최종적으로 최소 용량을 반환
