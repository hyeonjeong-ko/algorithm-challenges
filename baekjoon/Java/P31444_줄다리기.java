import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
* “같은 팀에 들어가서는 안 되는” 쌍은 팀워크가<t인 경우뿐

이 쌍들을 그래프의 간선으로 놓고,
“간선으로 연결된 두 노드는 서로 다른 색(팀)으로 칠해야 한다”

→ 바로 이분 그래프 검사 문제로 전환
*
* */
public class P31444_줄다리기 {
    static int N;
    static int[][] A;
    static int[] thresholds;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        List<Integer> list = new ArrayList<>();

        // 입력 읽기: 배열A, 팀워크값들 수집
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (i < j) list.add(A[i][j]);
            }
        }

        // 중복제거,정렬
        Collections.sort(list);
        thresholds = list.stream().distinct().mapToInt(x->x).toArray();

        // 이분탐색으로 최대 t 찾기
        int lo = 0, hi = thresholds.length - 1, ans = thresholds[0];
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int t = thresholds[mid];
            if (canPartition(t)) {
                ans = t;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(ans);
    }

    // threshold 미만의 A[u][v] 만 체크-> 서로다른 그래프 여야함.이때 이분그래프인지 검사.
    private static boolean canPartition(int threshold) {
        int[] color = new int[N]; // 0(미방문),-1(팀A),1(팀B)
        Deque<Integer> q = new ArrayDeque<>();
        Arrays.fill(color, 0);
        for(int i = 0; i < N; i++) {
            if (color[i] != 0) continue;
            color[i] = 1; // 방문
            q.clear();
            q.add(i);

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next = 0; next < N; next++) {
                    if (cur==next || A[cur][next] >= threshold) continue; // 팀워크<threshold만 연결간선(그래프)취급
                    if (color[next] == 0) { // 미방문 노드 탐색
                        color[next] = -color[cur]; // 다른 팀
                        q.add(next);
                    } else if (color[cur] == color[next]) {
                        return false; // 같은 색이어야 할 간선이 모순
                    }
                }
            }
        }
        return true;
    }
}
