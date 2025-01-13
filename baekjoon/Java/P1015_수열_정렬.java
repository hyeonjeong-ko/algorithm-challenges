import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		// 배열 A 입력받기
		ArrayList<Node0113> A = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A.add(new Node0113(Integer.parseInt(st.nextToken()), i));
		}

		A.sort((o1,o2) -> o1.v - o2.v);

		// 각 숫자가 몇 번째로 작은지(순위를 기록하는 배열)
		int[] P = new int[N];
		for (int i = 0; i < N; i++) {
			//정렬된 리스트에서 원래 인덱스 = 정렬된 배열에서의 순서
			P[A.get(i).idx] = i;
		}
		for (int i = 0; i < N; i++) {
			System.out.print(P[i] + " ");
		}

	}
}

class Node0113 {
	int v;
	int idx;

	public Node0113(int v, int idx) {
		this.v=v;
		this.idx=idx;
	}
}
