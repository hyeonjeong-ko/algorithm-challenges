import java.util.Arrays;
import java.util.Scanner;

public class P_1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] A = new int[N]; // A 배열 입력받기
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A); // 정렬

        int M = sc.nextInt();

        // 각 숫자를 받아, 숫자가 있는지 검사
        for (int i = 0; i < M; i++) {
            int target = sc.nextInt();

            // 이진 탐색
            System.out.println(binarySearch(A,target) );
//            int ans = Arrays.binarySearch(A, target) < 0 ? 0 : 1;
//            System.out.println(ans);
        }
    }
    public static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (target > arr[mid]) {
                l = mid + 1;
            } else if (target < arr[mid]){
                r = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
