// 중복조합, 백트래킹

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<List<Integer>> ans = new ArrayList<>();
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		dfs(0, 1, new ArrayList<>());

		System.out.print(sb.toString());
		sc.close();
	}

	public static void dfs(int n, int s, List<Integer> lst) {
		if (n == M) {
			// ans.add(new ArrayList<>(lst));
			for (int num : lst) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		// s부터 N까지 반복
		for (int j = s; j <= N; j++) {
			lst.add(j);
			dfs(n + 1, j, lst);
			lst.remove(lst.size()-1);
		}
	}
}
