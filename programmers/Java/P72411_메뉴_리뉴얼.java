// 해시맵 & 조합

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class 메뉴_리뉴얼 {
	static List<String> answer = new ArrayList<>();

	public String[] solution(String[] orders, int[] course) {

		// 각 course 길이마다 반복
		for (int k : course) {

			// 글자조합 : 개수
			Map<String, Integer> combinationCount = new HashMap<>();

			// 각 주문에서 길이 k 조합 추출
			for (String order : orders) {
				// 주문을 알파벳 순으로 정렬
				char[] orderChars = order.toCharArray();
				Arrays.sort(orderChars);
				String sortedOrder = new String(orderChars);
				
				// 길이 k의 조합 생성 (ex) "AB":1
				generateCombinations(sortedOrder, k, 0, "", combinationCount);
			}
			// 등장한 조합 중에서 최대 등장 횟수 찾기
			int maxCount = 0;
			for (int count : combinationCount.values()) {
				if (count > maxCount)
					maxCount = count;
			}
			// 최대 등장 횟수가 2이상인 조합을 결과에 추가
			if (maxCount >= 2) {
				for (Map.Entry<String, Integer> entry : combinationCount.entrySet()) {
					if (entry.getValue() == maxCount) {
						answer.add(entry.getKey());
					}
				}
			}

		}
		// 결과를 사전 순으로 정렬
		Collections.sort(answer);
		// 리스트를 배열로 변환하여 반환
		return answer.toArray(new String[0]);
	}

	// 조합만들전체 글자, course 길이 (조합개수), 탐색시작점, 현재문자열
	private void generateCombinations(String sortedOrder, int length, int start, String current, Map<String, Integer> combinationCount) {
		if (current.length() == length) {
			combinationCount.put(current, combinationCount.getOrDefault(current,0) + 1);
			// System.out.println("Generated Combination: " + current); // 조합 로그 출력
			return;
		}

		// 가능한 모든 조합 생성
		for (int i = start; i < sortedOrder.length(); i++) {
			generateCombinations(sortedOrder, length, i + 1, current + sortedOrder.charAt(i), combinationCount);
		}
	}
}
