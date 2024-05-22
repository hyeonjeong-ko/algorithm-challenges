import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int C = sc.nextInt();
        int R = sc.nextInt();
        
        ArrayList<Integer> rList = new ArrayList<>(Arrays.asList(0,R));
        ArrayList<Integer> cList = new ArrayList<>(Arrays.asList(0,C));
        
        int N = sc.nextInt();
        for (int i = 0; i < N; i++){
            int t = sc.nextInt();
            int n = sc.nextInt();
            
            if(t == 0){
                rList.add(n);
            }else{
                cList.add(n);
            }
        }
        Collections.sort(rList);
        Collections.sort(cList);
        
        int rMax = 0;
        for(int i = 1; i < rList.size(); i++){
            rMax = Math.max(rMax, rList.get(i) - rList.get(i - 1));
        }
        int cMax = 0;
        for (int i = 1; i < cList.size(); i++) {
            cMax = Math.max(cMax, cList.get(i) - cList.get(i - 1));
        }
        System.out.println(rMax * cMax);
        
        sc.close();
        
    }
}
