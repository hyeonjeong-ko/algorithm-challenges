/*
A solution to calculate the maximum area of a histogram formed by columns using their heights.

Metadata
- **Difficulty**: Medium
- **Time Taken**: 1 Hr
- **Answer Rate**: 51.977%

Analysis
**Input**:
- A list of integers representing the heights of the columns.

**Expected Time Complexity**: O(N * log(N))

**Implementation**
- **Data Structures**:
  - Use Euclidean algorithm to compute the Greatest Common Divisor (GCD).

- **Algorithms**:
  - Compute cumulative GCD from the left and from the right to determine the maximum answer.

**Note**:
- Cumulative sums are used to facilitate the GCD calculations.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 2];
        int[] lgcd = new int[N + 2];
        int[] rgcd = new int[N + 2];
        int[] res = new int[N + 2];

        for (int i = 1; i <= N; i++) { // 배열 입력받기
                arr[i] = Integer.parseInt(st.nextToken());
            }

        // 누적 최대공약수 계산
        for(int i=1; i<=N; i++) {
            lgcd[i] = gcd(arr[i], lgcd[i-1]);
        }
        for(int i=N; i>0; i--) {
            rgcd[i] = gcd(arr[i],rgcd[i+1]);
        }
        // 자신을 뺀 최대 공약수
        for(int i=1; i<=N; i++) {
         res[i] = gcd(lgcd[i - 1],rgcd[i + 1]);
        }
        // res 중 가장 큰 값 + 최대공약수 k 약수 x
        int maxNum = 0;
        int resIndex = -1;
        for(int i=1; i<=N; i++) {
            if ((maxNum < res[i]) && (arr[i] % res[i] != 0)) {
                maxNum = res[i];
                resIndex = i;
            }
        }
        if (resIndex == -1) {
            System.out.println("-1");
        }else {
            System.out.println(res[resIndex] + " " + arr[resIndex]);
        }

        }
    public static int gcd(int a,int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
