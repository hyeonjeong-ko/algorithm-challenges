import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class P_1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        sc.close();

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 1;i <=N;i++){
            deque.addLast(i); // deque list 초기화
        }

        StringBuilder answer = new StringBuilder();
        answer.append("<");

        while(!deque.isEmpty()){
            for(int i=0;i<K-1;i++){ // 회전
                deque.addLast(deque.removeFirst());
            }
            answer.append(deque.removeFirst());
            if(!deque.isEmpty()) answer.append(", ");
        }
        answer.append(">");
        System.out.println(answer.toString());
    }
}
