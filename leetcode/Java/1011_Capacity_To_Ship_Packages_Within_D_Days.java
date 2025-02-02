class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // 이진 탐색의 범위 설정
        int left = 0;
        int right = 0;
        
        // left는 최소 배 용량 (가장 무거운 짐의 무게)
        // right는 최대 배 용량 (모든 짐의 합)
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        // 이진 탐색을 시작
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canShip(weights, days, mid)) {
                right = mid;  // 가능하면 더 작은 용량을 찾음
            } else {
                left = mid + 1;  // 불가능하면 더 큰 용량을 찾음
            }
        }

        return left;  // 최소 용량 반환
    }

    // 배 용량이 주어졌을 때, 주어진 날수 내에 짐을 실을 수 있는지 확인하는 함수
    private boolean canShip(int[] weights, int days, int capacity) {
        int daysNeeded = 1;  // 첫 날부터 시작
        int currentWeight = 0;  // 첫 날 짐을 실을 무게
        
        for (int weight : weights) {
            if (currentWeight + weight > capacity) {  // 현재 짐을 실을 수 없으면
                daysNeeded++;  // 하루 더 추가
                currentWeight = 0;  // 새로 배에 짐을 실기 시작
            }
            currentWeight += weight;  // 짐을 실음
        }

        return daysNeeded <= days;  // 주어진 날수 내에 짐을 실을 수 있으면 true 반환
    }
}
