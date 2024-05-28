import java.util.Scanner;

/**
* N - Queen
* */
public class P_9663 {
    static boolean[] v1;
    static boolean[] v2;
    static boolean[] v3;
    static int ans;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        v1=new boolean[N];
        v2=new boolean[N*2];
        v3=new boolean[N*2];

        ans = 0;
        dfs(0);
        System.out.println(ans);
    }
    public static void dfs(int row){
        // 종료조건
        if(row == N){
            ans+=1;
            return;
        }
        for(int j = 0; j < N; j++){
            if(v1[j]==false && v2[j+row]==false && v3[row-j+N]==false){
                v1[j]=true;
                v2[j+row]=true;
                v3[row-j+N]=true;
                dfs(row + 1);
                v1[j]=false;
                v2[j+row]=false;
                v3[row-j+N]=false;
            }
        }
    }
}
