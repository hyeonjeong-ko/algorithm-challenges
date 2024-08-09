import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
* segment Tree
* Time Complexity: O(N + (M + K) * log N); 트리초기화 + 쿼리당 연산
* (A * B) % C = (A % C) * (B % C) % C
* 1-based, 곱셈이므로 트리를 0이 아닌 1로 초기화
*/

public class Main {
    static int N, M, K;
    static int leftStartIndex;
    static long[] tree;
    static int[] arr;
    static long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) { // N개 수 입력받기
            arr[i] = Integer.parseInt(br.readLine());
        }

        // tree 만들기
        int k = findK(N);
        int treeSize = (int) Math.pow(2, k + 1); // 2^(k+1)
        tree = new long[treeSize]; // 트리 생성

        leftStartIndex = (int) Math.pow(2, k);
        Arrays.fill(tree, 1);
        for (int i = leftStartIndex; i < leftStartIndex + N; i++) {
            tree[i] = arr[i - leftStartIndex + 1]; // 1-based
        }

        setTree(tree.length - 1); // 마지막 인덱스 전달

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            b = b + (int) Math.pow(2, k) - 1; // 질의 인덱스 변경

            if (a == 1) {
                changeVal(b, c);
            } else if (a == 2) {
                c = c + (int) Math.pow(2, k) - 1; // 질의 인덱스 변경
                sb.append(getProduct(b, c) % MOD).append("\n");
            }
        }

        System.out.print(sb.toString()); // 모든 출력을 한 번에 출력
    }

    private static void changeVal(int idx, int val) {
        tree[idx] = val;
        while (idx > 1) {
            idx /= 2;
            tree[idx] = (tree[idx * 2] * tree[idx  * 2 + 1]) % MOD;
        }
    }

    private static int findK(int n) {
        int k = 0;
        int ans = 1;
        while (ans < n) {
            k++;
            ans = (int) Math.pow(2, k);
        }
        return k;
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] = (tree[i / 2] * tree[i]) % MOD;

            i--;
        }
    }

    private static long getProduct(int s, int e) {
        long ans = 1;
        while (s <= e) {
            if (s % 2 == 1) {
                ans = (ans * tree[s]) % MOD;
                s++;
            }
            if (e % 2 == 0) {
                ans = (ans * tree[e]) % MOD;
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return ans % MOD;
    }
}
