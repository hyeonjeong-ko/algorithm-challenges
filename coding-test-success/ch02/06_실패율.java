import java.util.HashMap;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] levels = new int[N + 2];
        
        // 스테이지별 사람 수 구하기
        for (int i = 0; i < stages.length; i++) {
            levels[stages[i]] += 1;
        }
        
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;
        
        // 레벨을 순회하며 실패율 계산
        for (int i = 1; i <= N; i++) {
            if(levels[i] == 0) {
                fails.put(i, 0.);
            } else {
                fails.put(i, levels[i] / total);
                total -= levels[i];
            }
        }
        // 실패율이 높은 스테이지부터 내림차순 정렬
        return fails.entrySet().stream().sorted((o1,o2) -> Double.compare(o2.getValue(),o1.getValue()))
            .mapToInt(HashMap.Entry::getKey).toArray();
    }
}
