// 구현1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 베스트셀러 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 책 개수 입력
		int n = Integer.parseInt(br.readLine());

		// 2. 책 제목:빈도
		Map<String, Integer> bookCount = new HashMap<>();

		// 3. 책 제목 입력 및 빈도 계산
		for (int i = 0; i < n; i++) {
			String book = br.readLine();
			bookCount.put(book, bookCount.getOrDefault(book,0) + 1);
		}

		// 가장많이 팔린, 사전순 정렬
		ArrayList<String> bookNames = new ArrayList<>(bookCount.keySet());
		Collections.sort(bookNames);
		bookNames.sort((o1,o2)-> //판매량 많은순
			bookCount.get(o2).compareTo(bookCount.get(o1))
		);
		System.out.println(bookNames.get(0));
	}
}
