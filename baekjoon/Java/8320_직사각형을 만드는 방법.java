import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = N;
        for (int i = 2; i < N; i++){
            int tmp = N / i ;
            int n = tmp - (i-1);
            if(n < 1) break;
            ans += n;
        }
        System.out.println(ans);
    }
}
