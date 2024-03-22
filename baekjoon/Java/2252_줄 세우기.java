import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int E = sc.nextInt();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>(); // 위상 정렬
        
        for (int i = 0; i <= N; i++){
            A.add(new ArrayList<>());
        }
        int[] indegree = new int[N + 1]; // 진입차수 배열
        for (int i = 0; i < E; i++){
            int S = sc.nextInt();
            int M = sc.nextInt();
            A.get(S).add(M);
            indegree[M]++; // 진입 차수 배열 데이터 저장
        }
        for (int i = 1; i <= N; i++){
            if (indegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");
            for (int next : A.get(now)){
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }
    }
}
