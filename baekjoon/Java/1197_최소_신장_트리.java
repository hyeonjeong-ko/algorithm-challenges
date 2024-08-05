import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int[] parent;
    static PriorityQueue<pEdge> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 수
        int M = sc.nextInt(); // 에지 수
        queue = new PriorityQueue<>();
        parent = new int[N + 1];
        for (int i = 0; i < N; i++) parent[i] = i; // 대표 노드 초기화
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            queue.add(new pEdge(s, e, v));
        }
        int useEdge = 0;
        int result = 0;
        while (useEdge < N - 1) { // 간선 개수 N - 1
            pEdge now = queue.poll();
            if (find(now.s) != find(now.e)) { // 같은 부모가 아니라면 싸이클 만들어지지 않는다.
                union(now.s, now.e);
                result = result + now.v;
                useEdge++;
            }
        }
        System.out.println(result);

    }
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a!=b) parent[b] = a;
    }
    public static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}

class pEdge implements Comparable<pEdge> {
    int s,e,v;

    public pEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(pEdge pEdge) {
        return this.v - pEdge.v;
    }
}
