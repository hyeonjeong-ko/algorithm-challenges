import java.util.Scanner;

public class 스위치_켜고_끄기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력 처리
		int N = sc.nextInt(); // 스위치 개수
		int[] switches = new int[N + 1]; // 1-based
		for (int i = 1; i <= N; i++) {
			switches[i] = sc.nextInt();
		}

		int studentCnt = sc.nextInt(); // 학생 수

		// 학생 정보 처리
		for (int s = 0; s < studentCnt; s++) {
			int studentType = sc.nextInt();
			int number = sc.nextInt();

			if (studentType == 1) {
				// 남학생
				for (int i = number; i <= N; i += number) {
					switches[i] = 1 - switches[i]; // 상태 반전
				}
			} else if (studentType == 2) {
				// 여학생
				int idx = 0;
				// 범위 내에서 대칭 구간 찾기
				while (number - idx >= 1 && number + idx <= N && switches[number - idx] == switches[number + idx]) {
					idx++;
				}

				// idx가 1 증가한 후 멈추므로 실제 대칭 범위는 idx - 1
				idx--;

				// 대칭 범위의 모든 스위치 상태를 반전
				for (int i = number - idx; i <= number + idx; i++) {
					switches[i] = 1 - switches[i];
				}
			}
		}

		// 최종 스위치 상태를 20개씩 출력
		for (int i = 1; i <= N; i++) {
			System.out.print(switches[i] + " ");
			if (i % 20 == 0) { // 20개마다 줄바꿈
				System.out.println();
			}
		}
		sc.close();
	}
}
