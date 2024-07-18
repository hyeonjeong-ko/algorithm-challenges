import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = -1;

        // 5kg 봉지 최대개수부터 0까지 역순조회
        for (int cnt_5 = N / 5; cnt_5 >= 0; cnt_5--) {
            // 남은 무게 3kg 봉지
            int cnt_3 = (N - (cnt_5 * 5)) / 3;
            if (cnt_5 * 5 + cnt_3 * 3 == N) {
                result = cnt_5 + cnt_3;
                break;  // 조건이 참일 때만 루프 종료
            }
        }
        System.out.println(result);
    }
}
