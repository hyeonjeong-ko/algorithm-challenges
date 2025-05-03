// str, simul, map
// 단위를 Day로 통일해 계산해주기

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class P150370_개인정보_수집_유효기간 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        // today : 오늘 날짜
        int todayIntValue = dateToInt(today);

        // terms: 유효기간 ["A 6","B 12", ...]
        Map<String, Integer> termsStorage = new HashMap<>();
        for (String term : terms) {
            String[] termInfo = term.split(" ");
            // day 기준으로 값 넣기
            termsStorage.put(termInfo[0], Integer.parseInt(termInfo[1]) * 28);
        }

        ArrayList<Integer> destroyList = new ArrayList<>();

        // privacies : 개인정보
        for (int i = 0; i < privacies.length; i++) {
            String[] privacyInfo = privacies[i].split(" ");

            // 날짜 -> Day로 변환, 비교
            int privacyCollectionDay = dateToInt(privacyInfo[0]);
            int termPeriod = termsStorage.get(privacyInfo[1]);

            if (privacyCollectionDay + termPeriod <= todayIntValue) {
                destroyList.add(i + 1);
            }
        }
        answer = new int[destroyList.size()];
        for (int i = 0; i < destroyList.size(); i++) {
            answer[i] = destroyList.get(i);
        }
        return answer;
    }

    public int dateToInt(String date) {
        String[] dateInfo = date.split("\\.");
        int year = Integer.parseInt(dateInfo[0].substring(2, 4)); // ex_ 2022 -> 22
        int month = Integer.parseInt(dateInfo[1]);
        int day = Integer.parseInt(dateInfo[2]);
        return year * 12 * 28 + month * 28 + day;
    }
}
