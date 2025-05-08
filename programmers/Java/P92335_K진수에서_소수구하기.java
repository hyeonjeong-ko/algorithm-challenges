public class P92335_K진수_소수 {
    public int solution(int n, int k) {
        // k진수 문자열 변환
        String numStr = (k == 10) ? String.valueOf(n) : toBaseK(n,k);

        // '0'을 기준으로 잘라 빈 문자열 제거
        String[] parts = numStr.split("0");
        int answer = 0;
        for (String part : parts) {
            if (part.isEmpty()) continue;
            long val = Long.parseLong(part);
            if (isPrime(val)) answer++;
        }


        return answer;
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true; // 2는 소수
        if (num % 2 == 0) return false;
        long limit = (long) Math.sqrt(num);
        for (int i = 3; i <= limit; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    private String toBaseK(int n, int base) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % base);
            n /= base;
        }
        return sb.length() == 0 ?
                "0"
                : sb.reverse().toString();
    }
}
