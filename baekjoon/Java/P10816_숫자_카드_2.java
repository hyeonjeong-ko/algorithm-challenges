import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P10816 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());  // 상근이가 가진 숫자 카드의 개수
		HashMap<Integer, Integer> cardCountMap = new HashMap<>();  // 숫자 카드의 개수를 저장할 해시맵

		// 숫자 카드 입력 및 개수 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int card = Integer.parseInt(st.nextToken());
			cardCountMap.put(card, cardCountMap.getOrDefault(card, 0) + 1); // 기존 개수에 +1
		}

		int M = Integer.parseInt(br.readLine());  // 구해야 할 수의 개수
		StringBuilder sb = new StringBuilder();

		// 찾으려는 숫자 카드 입력 및 개수 출력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int query = Integer.parseInt(st.nextToken());
			sb.append(cardCountMap.getOrDefault(query, 0)).append(" ");
		}

		System.out.println(sb.toString().trim());  // 결과 출력
	}
}

// ============================================================================================================

public class P10816_2 {
	public static void main(String[] args) throws IOException {
		int arr[] = new int[20000001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			arr[now + 10000000]++; // value->idx로 표현
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int now = Integer.parseInt(st.nextToken());
			sb.append(arr[now + 10000000] + " ");
		}

		System.out.println(sb.toString().trim());
	}
}
