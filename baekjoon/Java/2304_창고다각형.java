/*
A solution to calculate the area formed by columns using their heights.

Metadata
- **Difficulty**: Medium
- **Time Taken**: 1 Hr

Analysis
**Input**:
- A list of pairs `(L, H)`, where `L` is the position of a column and `H` is its height

**Expected Time Complexity**:  1<= L,H <=1,000

**Implementation**
- **Data Structures**: 
  - An array `lst` of size 1001 to store the height of columns at respective positions
- **Algorithms**:
  - Two-pass traversal to calculate the area:
    1. From the left up to the tallest column
    2. From the right up to the tallest column
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] lst = new int[1001];

        int mx_i = 0;
        int mx = 0;

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int L = Integer.parseInt(line[0]); // Position
            int H = Integer.parseInt(line[1]); // Height
            lst[L] = H;
            
            if (mx < H) {
                mx_i = L;
                mx = H;
            }
        }

        int ans = 0;
        mx = 0;
        for (int i = 0; i <= mx_i; i++) {
            mx = Math.max(mx, lst[i]);
            ans += mx;
        }
        mx = 0;
        for (int i = 1000; i > mx_i; i--) {
            mx = Math.max(mx, lst[i]);
            ans += mx;
        }
        System.out.println(ans);
    }
}
