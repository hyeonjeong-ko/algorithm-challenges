// 구현 문제; N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        // 100보다 작은 수는 모두 한수
        int cnt = Integer.parseInt(n);
        if (cnt > 99) {
            cnt = 99;
        } else {
            System.out.println(cnt); // 99 이하는 모두 한수
            return;
        }
        
        // 각 숫자를 모두 한수인지 검사, 맞으면 cnt++
        for(int i=100; i <=Integer.parseInt(n); i++) {
            char[] c = String.valueOf(i).toCharArray();
            boolean hansu = true;
            int diff = c[0] - c[1];
            for(int j=1; j<c.length-1; j++) {
                if(c[j] - c[j+1]!=diff) {
                    hansu = false;
                    break;
                }
            }
            if (hansu) {cnt++;}   
        }
        System.out.println(cnt);
    }
}
