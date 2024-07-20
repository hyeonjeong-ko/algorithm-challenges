import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] lst = new int[N];
        for (int i = 0; i < N; i++) {
            lst[i] = Integer.parseInt(st.nextToken());
        }

        int sm = 0;
        for (int i = 0; i < K; i++) sm += lst[i];

        int ans = sm;

        for (int i = K; i < N; i++) {
            sm = sm + lst[i] - lst[i - K];
            ans = Math.max(ans, sm);
        }
        System.out.println(ans);
    }
}
