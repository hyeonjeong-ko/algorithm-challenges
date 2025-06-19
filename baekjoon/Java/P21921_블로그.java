
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* algo: sliding window
* */

public class P21921_블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력받기
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 윈도우합
        long sum = 0;
        for (int i = 0; i < X; i++) {
            sum += arr[i];
        }
        long maxSum = sum;
        long cnt = 1;

        for (int i = X; i < N; i++) {
            sum += arr[i] - arr[i - X]; // 슬라이딩 윈도우
            if (sum > maxSum) {
                maxSum = sum;
                cnt = 1;
            } else if (sum == maxSum) {
                cnt++;
            }
        }

        if (maxSum == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxSum);
            System.out.println(cnt);
        }
    }
}
