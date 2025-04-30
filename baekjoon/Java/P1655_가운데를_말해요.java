import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class P1655_가운데를_말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        // 작은 절반 관리 (최대힙)
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        // 큰 절반 관리 (최소힙)
        PriorityQueue<Integer> right = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            // 1. 크기 기준 삽입
            if (left.size() == right.size()) {
                left.offer(x);
            } else {
                right.offer(x);
            }

            // 2. 경계값 조정
            if (!right.isEmpty() && left.peek() > right.peek()) {
                int a = left.poll();
                int b = right.poll();
                left.offer(b);
                right.offer(a);
            }
            // 중간값 누적
            sb.append(left.peek()).append("\n");
        }
        System.out.print(sb);
    }
}
