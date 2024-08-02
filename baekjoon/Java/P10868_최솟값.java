/**
 * A solution to the segment minimum query problem.
 *
 * Metadata
 * Difficulty: Intermediate
 * Time Taken: 3 hr
 * Correct Answer Rate: 53.880%
 *
 * Analysis
 * Input:
 * Two integers N (number of elements) and M (number of queries).
 * N integers representing the array elements.
 * M pairs of integers a and b representing the query ranges.
 * Expected Time Complexity: O((N + M) log N)
 *
 * Implementation
 * Data Structures: Segment Tree
 * Algorithms: Segment Tree construction and query
 *
 * Result
 * Time Complexity: O((N + M) log N)
 *
 * More
 * Key insights:
 * Efficient range minimum queries using a segment tree.
 * Constructing the segment tree with leaf nodes representing the input array and internal nodes storing the minimum values of their child nodes.
 * Handling range queries by traversing the segment tree from the leaf nodes up to the root.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_10868 {
    static int[] tree;
    static int treeSize;
    static long k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        for(int i = 0; i < N; i++) { // 입력값 받기
            data[i] = Integer.parseInt(br.readLine());
        }
        // 1. TreeSize 2^k >= N; 트리사이즈; 2 ^k*2
        k = findK(N);
        treeSize = (int) Math.pow(2, k + 1);

        // 2. 트리 초기화
        int leftNodeStartIndex = (int) Math.pow(2, k); //시작인덱스 = 2^k
        tree = new int[treeSize];

        // 3. 데이터 리프노드에 입력받기
        for(int i = leftNodeStartIndex; i < leftNodeStartIndex + N; i++) {
            tree[i] = data[i - leftNodeStartIndex];
        }

        setTree(treeSize - 1); // 부모 노드 채우기 (맨뒤인덱스 전달)
//        for (long i : tree) {
//            System.out.print(i + " ");
//        }

        int ans = 0;
        // 질의 값 구하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            //4. 질의 index 트리에 맞게 변경; 질의인덱스 + 2^k - 1
            int startIdx = s + (int)Math.pow(2, k) - 1;
            int endIdx = e + (int)Math.pow(2, k) - 1;

            System.out.println(getMin(startIdx, endIdx));
        }

    }

    private static int getMin(int s, int e) { // 선택노드들 중 최솟값을 비교해 출력한다.
        int min = Integer.MAX_VALUE;
//        System.out.println("s + e = " + s + " " + e);
//        System.out.println("tree[s] + tree[e] = " + tree[s] + tree[e]);
        while (s <= e) {
            if (s % 2 == 1) {
                min = (int) Math.min(min, tree[s]);
                s++;
            }
            if (e % 2 == 0) {
                min = (int) Math.min(min, tree[e]);
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return min;
    }

    private static void setTree(int i) { // 초기 트리 생성
        while(i > 1) { // 인덱스가 루트가 아닐때까지
//            System.out.println("tree " + i + " :" + tree[i]);
            tree[i / 2] = Math.min(tree[i], tree[i - 1]); // 부모 최솟값을 갱신
            i-=2;
        }

//        while(i != 1) {
//            if(tree[i / 2] > tree[i]) {
//                tree[i / 2] = tree[i];
//                i--;
//            }
//        }
    }

    public static long findK(int N) {
        k = 0;
        int powOfTwo = 1;
        while (powOfTwo < N) {
            k+=1;
            powOfTwo=(int) Math.pow(2, k);
        }
        return k;
    }
}
