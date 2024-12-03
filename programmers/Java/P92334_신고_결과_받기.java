// HashMap

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class 신고_결과_받기 {
	public int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		LinkedHashMap<String, HashSet<String>> reportedMap = new LinkedHashMap<>();

		// 신고당한사람 : 신고자목록
		for (int i = 0; i < report.length; i++) {
			String[] split = report[i].split(" ");
			String userId = split[0];
			String reportedId = split[1];

			// reportedMap 에 신고자 추가
			reportedMap.putIfAbsent(reportedId, new HashSet<>());
			reportedMap.get(reportedId).add(userId);
		}

		// 신고자 : 메일수신횟수
		HashMap<String, Integer> reportCnt = new HashMap<>();
		for (Map.Entry<String, HashSet<String>> entry : reportedMap.entrySet()) {
			String reportedId = entry.getKey(); // 신고당한 사람
			HashSet<String> reporters = entry.getValue(); // 신고자 목록

			if (reporters.size() >= k) { // 신고 횟수가 k 이상인 경우
				for (String reporter : reporters) {
					// 신고자에게 알림 증가
					reportCnt.put(reporter, reportCnt.getOrDefault(reporter,0) + 1);
				}
			}
		}
		// 결과 출력
		for (int i = 0; i < id_list.length; i++) {
			String userId = id_list[i];
			answer[i] = reportCnt.getOrDefault(userId,0); // 알림 횟수 가져오기
		}
		return answer;
	}
}
