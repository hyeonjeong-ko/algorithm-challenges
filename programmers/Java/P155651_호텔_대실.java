/*
  메타데이터
  난이도: 중급
  소요 시간: 1시간
  정답률: Medium
  
  분석
  입력: 
    - 예약된 대실 시간을 나타내는 리스트 (형식: [["시작시간", "종료시간"], ...])
  기대 시간 복잡도: O(N)
  
  구현
  자료구조: min Heap
  알고리즘: 힙(Heap)을 이용한 시뮬레이션
  
  결과
  시간 복잡도: O(N log N)
*/
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 예약 시간을 오름차순으로 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        // 종료 시간을 저장할 우선순위 큐
        PriorityQueue<Integer> ends = new PriorityQueue<>();

        for (String[] time : book_time) {
            String start = time[0];
            String end = time[1];

            // 시작 시간을 분 단위로 변환
            int startMinutes = convertToMinutes(start);

            // 가장 빠른 종료 시간이 현재 시작 시간보다 작거나 같으면 회의실 사용 가능
            if (!ends.isEmpty() && ends.peek() <= startMinutes) {
                ends.poll(); // 가장 이른 종료 시간 제거
            }

            // 종료 시간을 분 단위로 변환 후, 10분 추가
            ends.add(convertToMinutes(end) + 10);
        }

        return ends.size(); // 현재 예약에 필요한 회의실 수
    }

    // 시간을 "HH:MM" 형식에서 분 단위로 변환하는 함수
    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
