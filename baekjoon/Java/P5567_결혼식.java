/*
* basic - bfs
*/

import java.util.*;

public class P_5567 {
    static int n;
    static int m;
    static int cnt = 0;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 동기 수
        m = sc.nextInt(); // 친구 관계 수
        visited = new boolean[n + 1];

        // 엣지리스트 초기화
        List<List<Integer>> friends = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            friends.add(new ArrayList<>());
        }
        // 엣지 정보 입력받기
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            friends.get(a).add(b);
            friends.get(b).add(a); // 양방향
        }

        // bfs
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int friend : friends.get(curr)) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    q.add(friend);
                    // 상근이(1) 친구, 친구의 친구만 초대하도록 깊이 제한
                    if (curr == 1 || friends.get(1).contains(curr)) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
