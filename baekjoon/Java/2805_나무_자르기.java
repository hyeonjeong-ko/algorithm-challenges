/*
- **Difficulty**: Medium ~ Hard
- **Time Taken**: 30 min
- **Answer Rate**: 26.328%
Analysis
**Input**:
- Two integers `N` (number of trees) and `M` (minimum amount of wood needed)
- An array of integers representing the heights of the trees
**Expected Time Complexity**: O(N log N)
**Implementation**
- **Data Structures**:
  - `candidate` to store the current optimal height of the saw
- **Algorithms**:
  - **Binary Search**:
    - Initialize the left (`l`) and right (`r`) boundaries of the search space to 0 and the height of the tallest tree, respectively.
    - While `l` is less than or equal to `r`, calculate the mid-point (`mid`).
    - Compute the total amount of wood that would be collected if the saw were set to `mid` height.
    - If the collected wood is at least `M`:
      - Update `candidate` to the current `mid` as it is a valid maximum height.
      - Move the left boundary up to `mid + 1` to search for a potentially higher valid height.
    - Otherwise, move the right boundary down to `mid - 1` to search for a lower height.
    */
import java.util.Scanner;

public class P_2805 {
    static int candidate = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N];
        // 입력값 받기
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        binarySearch(arr,M);
        System.out.println(candidate);
    }

    public static void binarySearch(int[] arr, int M) {
        // 나무 높이의 최소값과 최대값
        int l = 0, r = 1000000000;

        while (l <= r) {
            int mid = (l + r) / 2; // mid는 답이 될 수 있는 높이 후보
            long minTree = 0; // 가져갈 수 있는 나무의 양

            // mid 기준으로 나무 자르기
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > mid) {
                    minTree += arr[i] - mid;
                }
            }

            if (minTree >= M) { // minTree가 M보다 크거나 같다면
                candidate = mid; // 현재 높이를 후보로 저장
                l = mid + 1; // 더 높은 높이 탐색
            } else {
                r = mid - 1; // 더 낮은 높이를 탐색
            }
        }
    }
}
