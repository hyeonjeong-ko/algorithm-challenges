/*
문제:
https://school.programmers.co.kr/learn/courses/30/lessons/42883?language=java

그리디: 큰 숫자를 앞자리에 배치해 가장 큰 숫자 만들기
단조 감조 스택: 스택 숫자가 내림차순을 유지하도록 작은 숫자 제거
시간 복잡도: O(n)
*/

import java.util.Stack;

public class 큰_수_만들기 {
	public String solution(String number, int k) {
		Stack<Character> st = new Stack<>();
		StringBuilder result = new StringBuilder();

		// 숫자를 한 자리씩 처리
		for (int i = 0; i < number.length(); i++) {
			char digit = number.charAt(i);

			// 스택 마지막 숫자보다 현재 숫자가 크면 제거 (k가 남아 있는 경우)
			while (!st.isEmpty() && st.peek() < digit && k > 0) {
				st.pop();
				k--;
			}

			// 현재 숫자를 스택에 추가
			st.push(digit);
		}

		// 만약 제거 횟수가 남아 있으면, 뒤에서 제거
		while (k > 0) {
			st.pop();
			k--;
		}
		// 스택 내용을 결과 문자열로 변환
		for (char digit : st) result.append((digit));

		return result.toString();
	}
}
