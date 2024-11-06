// 인접리스트로 만들고, DFS 탐색하며 각 노드의 부모기록

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static boolean[] visited;
	static ArrayList<Integer>[] tree;
	static int[] ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[N + 1];
		ans = new int[N + 1];
		tree = new ArrayList[N + 1];
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N-1; i++) { // 트리에는 N-1개의 간선이 있음
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			tree[n1].add(n2);
			tree[n2].add(n1);
		}
		DFS_11725(1); // 부모 노드부터 DFS

		for (int i = 2; i <= N; i++) {
			System.out.println(ans[i]);
		}
	}

	private static void DFS_11725(int n) {
		visited[n] = true;
		for (int i : tree[n]) {
			if(!visited[i]) {
				ans[i] = n; // DFS 탐색하며 부모 노드를 정답 배열에 저장
				DFS_11725(i);
			}
		}
	}
}
