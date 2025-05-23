// 문제핵심: 항상 범위값을 먼저 확인하자!
// algo: backtracking

import java.util.Scanner;

public class P10819_차이를_최대로 { 
    static int N;
    static int[] arr;
    static boolean[] visited;
    static int[] perm;
    static int max = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        visited = new boolean[N];
        perm = new int[N];
        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            max = Math.max(max, calc(perm));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            perm[depth] = arr[i];
            dfs(depth + 1);
            visited[i] = false;
        }
    }
    private static int calc(int[] arr) {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }
        return sum;
    }
}
