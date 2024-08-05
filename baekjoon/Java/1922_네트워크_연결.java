
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_1922 {
    static int N;
    static int E;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        PriorityQueue<pEdge> queue = new PriorityQueue<>();
        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) { // 대표 노드 초기화
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            queue.add(new pEdge(S,E,V));
        }
        int cost = 0;
        int useEdge = 0;
        while (useEdge < N - 1) {
            pEdge node = queue.poll();
            if (find(node.s) != find(node.e)) { // 싸이클 X
                union(node.s,node.e);
                useEdge++;
                cost += node.v;
            }
        }
        System.out.println(cost);
    }
    public static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    public static void union(int i, int j){
        int a = find(i);
        int b = find(j);
        if (a!=b) parent[b] = a;
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
