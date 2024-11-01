# 최소 신장 트리 (MST) 구현

이 Java 코드는 Kruskal 알고리즘과 Union-Find 구조를 사용하여 최소 신장 트리(Minimum Spanning Tree, MST)를 구하는 예제 템플릿입니다. 주어진 무방향 가중 그래프의 MST를 찾습니다.

## 코드 개요

- **노드 및 간선 입력**: 노드 수 (N)와 간선 수 (M)를 입력받습니다.
- **간선 가중치 정렬을 위한 우선순위 큐**: `PriorityQueue`를 사용하여 간선을 가중치 오름차순으로 정렬합니다.
- **Union-Find 연산**: 사이클을 방지하기 위해 서로소 집합의 노드를 합치거나 찾는 연산을 수행합니다.

## 주요 메서드

- **find(int a)**: 경로 압축을 사용하여 노드의 루트를 찾습니다.
- **union(int a, int b)**: 두 노드가 다른 컴포넌트에 속해 있다면 합쳐서 사이클이 생기지 않도록 합니다.
- **compareTo(pEdge pEdge)**: 가중치가 작은 간선을 우선으로 비교하여 선택합니다.

## 예제 코드

```java
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int[] parent;
    static PriorityQueue<pEdge> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 노드 수
        int M = sc.nextInt(); // 간선 수
        queue = new PriorityQueue<>();
        parent = new int[N + 1];
        for (int i = 0; i < N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            queue.add(new pEdge(s, e, v));
        }

        int useEdge = 0;
        int result = 0;
        while (useEdge < N - 1) {
            pEdge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result += now.v;
                useEdge++;
            }
        }
        System.out.println(result);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}

class pEdge implements Comparable<pEdge> {
    int s, e, v;

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
