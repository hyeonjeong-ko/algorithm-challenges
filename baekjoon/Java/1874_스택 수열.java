import java.util.Scanner;
import java.util.Stack;

public class P_1874 {
    static int num = 1;
    static boolean res = true;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열 길이
        int[] arr = new int[N];
        for(int i=0; i <N; i++){ // 수열 입력받기
            arr[i] = sc.nextInt();
        }

        Stack<Integer> st= new Stack<>();
        for(int i=0; i<N; i++){
            if(arr[i] >= num) { // 만들어야하는 수열숫자 > 현재자연수
                while(arr[i]>=num){ // 현재 수열값과 값이 같아질때까지 push()
                    st.push(num++);
                    sb.append("+\n");
                }
                //값이 같아졌다면 pop()
                st.pop();
                sb.append("-\n");
            }
            else{ // 만들어야하는 수열숫자 < 현재자연수
                int n = st.pop();
                if(n != arr[i]){ // pop()했는데 값이 같지않으면 수열출력불가능
                    System.out.println("NO");
                    res = false;
                    break;
                }else{
                    sb.append("-\n");
                }
            }
        }
        if (res) {
            System.out.println(sb.toString());
        }
    }
}
