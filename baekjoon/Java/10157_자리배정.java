/*
A solution to seating allocation problem.

Metadata
Difficulty: Intermediate
Time Taken: 1 hr
Correct Answer Rate: 39.834%

Analysis
Input: 
- 1 <= C, R <= 10^3 (number of columns and rows)
- 1 <= K <= R * C (target seat number)
Expected Time Complexity: O(R * C)

Implementation
Data Structures: 2D List
Algorithms: Simulation, Directional Movement
Statements: for loop, while

Result
Time Complexity: O(R * C)

More
- Key insights: Padding the grid with 1s to avoid boundary checks; Using (dr + 1) % 4 for direction changes.

 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();
        int R = sc.nextInt();
        int K = sc.nextInt();

        if(R*C < K) { // 좌석 부족
            System.out.println(0);
        }else {
            // 이동 방향: ↓,→,↑,←
            int[] di = {1, 0, -1, 0};
            int[] dj = {0, 1, 0, -1};

            int[][] arr = new int[R + 2][C + 2];
            for(int i = 0; i < R + 2; i++){
                for(int j = 0; j < C + 2; j++){
                    if(i == 0 || i == R + 1 || j == 0 || j == C + 1){
                        arr[i][j] = 1; // 경계 1로 채우기
                    }
                }
            }

            int ci = 1, cj = 1, dr = 0; //시작위치, 초기 방향
            for(int n = 1; n < K; n++){
                arr[ci][cj] = n; //좌석 번호 배정
                int ni = ci + di[dr];
                int nj = cj + dj[dr];

                if(arr[ni][nj] == 0){ //다음 셀이 비어있으면 이동
                    ci = ni;
                    cj = nj;
                }else{ //그렇지않으면 방향 변경
                    dr = (dr + 1)%4;
                    ci = ci + di[dr];
                    cj = cj + dj[dr];
                }
            }
            System.out.println(cj + " " + ci); //최종 좌표 출력
        }
        sc.close();
    }
}
