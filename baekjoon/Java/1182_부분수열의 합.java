import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ans=0;
    static int N,S;
    static int[] lst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        lst = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            lst[i] = Integer.parseInt(st.nextToken());
        }


        dfs(0,0,0);
        System.out.println(ans);
    }
    public static void dfs(int depth,int sum,int cnt){
        if(depth==N){
            if(sum==S && cnt>0){
                ans+=1;
            }
            return;
        }
        dfs(depth+1,sum+lst[depth],cnt+1);
        dfs(depth+1,sum,cnt);
    }
}
