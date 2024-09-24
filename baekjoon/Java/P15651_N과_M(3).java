/*
backtracking
*/
public class P15651 {
	static int N, M;
	static List<List<Integer>> ans = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		dfs_(0, new ArrayList<>());

		System.out.print(sb.toString());
	}
	static void dfs_(int depth, List<Integer> lst) {
		if (depth == M) {
			for (int num : lst) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int j = 1; j <=N; j++) {
			lst.add(j);
			dfs_(depth + 1, lst);
			lst.remove(lst.size()-1); // 백트래킹
		}
	}
}
