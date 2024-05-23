//https://www.acmicpc.net/problem/10431
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int tc = 1; tc <= T; tc++){
            int N = sc.nextInt();
            int[] lst = new int[20];
            for(int i = 0; i < 20; i++){
                lst[i] = sc.nextInt();
            }
            int cnt = 0;
            for(int i = 1; i < 20; i++){
                for(int j = 0; j < i; j++){
                    if(lst[i] < lst[j]) cnt++;
                }
            }
            System.out.println(tc + " " + cnt);
        }
        sc.close();
    }
}
