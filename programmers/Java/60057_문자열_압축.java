// brute-force

public class Solution {
    
    // 주어진 단위(unit)로 문자열을 압축하는 함수
    public static String compressString(String s, int unit) {
    StringBuilder compressed = new StringBuilder();
    String prev = s.substring(0, unit);
    int count = 1;

    for (int i = unit; i < s.length(); i += unit) {
        String curr = s.substring(i, Math.min(i + unit, s.length()));

        if (curr.equals(prev)) {
            count++;
        } else {
            compressed.append(count > 1 ? count + prev : prev);
            prev = curr;
            count = 1;
        }
    }

    compressed.append(count > 1 ? count + prev : prev); // 마지막 블록 추가
    return compressed.toString();
}


    // 문자열을 다양한 단위로 압축하고 가장 짧은 길이를 반환
    public static int solution(String s) {
        if (s.length() == 1) {
            return 1; // 길이가 1이면 압축 불가능
        }

        int minLength = s.length(); // 초기 최소 길이 (원래 길이)

        // 1부터 len(s)/2까지 단위별로 확인
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            String compressed = compressString(s, unit);
            minLength = Math.min(minLength, compressed.length());
        }

        return minLength;
    }
}
