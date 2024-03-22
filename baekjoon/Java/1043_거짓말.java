import java.util.*;

public class Main {
    public static int[] parent;
    public static int[] trueP;
    public static ArrayList<Integer>[] party;
    public static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        result = 0;
        trueP = new int[T];
        for(int i = 0; i < T; i++){ // 진실을 아는 사람들 저장
            trueP[i] = sc.nextInt();
        }
        party = new ArrayList[M];
        for (int i = 0; i < M; i++){
            party[i] = new ArrayList<Integer>(); // 파티 데이터 저장
            int party_size = sc.nextInt();
            for (int j = 0; j < party_size; j++){
                party[i].add(sc.nextInt());
            }
        }
        // 대표 노드를 자기 자신으로 초기화
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++){
            parent[i] = i;
        }
        for (int i = 0; i < M; i++){ // 파티 참여 그룹 union
            int first = party[i].get(0);
            for(int j = 1; j < party[i].size(); j++){
                union(first, party[i].get(j));
            }
        }
        // 진실아는 사람들이 각 파티에 한명이라도 있다면(대표노드 같다면) 거짓말 못한다.
        for (int i = 0; i < M; i++){
            boolean isPossible = true;
            int cur = party[i].get(0);
            for (int j = 0; j < trueP.length; j++){
                if(find(cur) == find(trueP[j])) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) result++; // 모두 다르면 결괏값 1 증가
        }
        System.out.println(result);
    }
    
    //union
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b){
            parent[b] = a;
        }
    }
    //find
    public static int find(int a){
        if (a==parent[a]) return a;
        else
            return parent[a] = find(parent[a]);  //재귀 함수
    }
    public static boolean checkSame(int a, int b){
        a = find(a);
        b = find(b);
        if (a==b){
            return true;
        }
        return false;
    }
}
