/**
segment Tree

초기화 시간 복잡도: O(N)
쿼리당 시간 복잡도: O(log N)
전체 시간 복잡도: O(N + M log N)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2357 {
    static int N, M, K, treeSize;
    static int[] minTree;
    static int[] maxTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. 2^k>=N, 2^k*2
        K = findK(N);
        treeSize = (int) Math.pow(2, K) * 2;

        // 2. 리프 노드값 채우기 (시작인덱스 2^k)
        int leftStartIndex = (int) Math.pow(2, K);
        minTree = new int[treeSize + 1];
        maxTree = new int[treeSize + 1];

        // 트리 전체를 무한대로 초기화
        for (int i = 1; i <= treeSize; i++) {
            minTree[i] = Integer.MAX_VALUE;
            maxTree[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            minTree[leftStartIndex + i] = val;
            maxTree[leftStartIndex + i] = val;
        }

        // 3. 트리 초기화
        minSetTree(treeSize - 1);
        maxSetTree(treeSize - 1);

        // 4. 질의 값 구하기
        for (int i = 0; i < M; i++) { // 구간 최솟값, 최댓값 구하기
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            start += (int)Math.pow(2, K) - 1;
            end += (int)Math.pow(2, K) - 1;

            sb.append(getMin(start, end)).append(" ")
                    .append(getMax(start, end)).append("\n");}

        System.out.println(sb.toString());

    }

    private static int getMax(int s, int e) {
        int res = Integer.MIN_VALUE;

        while (s <= e) {
            if (s % 2 == 1) {
                res = Math.max(maxTree[s], res);
                s++;
            }
            if (e % 2 == 0) {
                res = Math.max(maxTree[e], res);
                e--;
            }
            s /= 2; // depth 변경
            e /= 2; // depth 변경
        }
        return res;
    }
    private static int getMin(int s, int e) {
        int res = Integer.MAX_VALUE;

        while (s <= e) {
            if (s % 2 == 1) {
                res = Math.min(minTree[s], res);
                s++;
            }
            if (e % 2 == 0) {
                res = Math.min(minTree[e], res);
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return res;
    }

    private static void minSetTree(int i) {
        while (i > 1) {
            minTree[i / 2] = Math.min(minTree[i / 2], minTree[i]);
            i--;
        }
    }

    private static void maxSetTree(int i) {
        while (i > 1) {
            maxTree[i / 2] = Math.max(maxTree[i / 2], maxTree[i]);
            i--;
        }
    }

    private static int findK(int n) {
        int k = 0;
        int powerOfTwo = 1; // 2^k
        while (powerOfTwo < n) {
            k++;
            powerOfTwo = (int) Math.pow(2, k);
        }
        return k;
    }
}
