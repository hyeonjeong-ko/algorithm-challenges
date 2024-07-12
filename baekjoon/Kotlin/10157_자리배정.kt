"""A solution to seating allocation problem.

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
"""


import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (C,R) = reader.readLine().split(" ").map{ it.toInt() }
    val K = reader.readLine().toInt()

    // 좌석 부족한 경우 0 출력
    if(R * C < K) {
        println(0)
    }else {
        // 이동 방향: 아래, 오른쪽, 위, 왼쪽
        val di = arrayOf(1, 0, -1, 0)
        val dj = arrayOf(0, 1, 0, -1)

        // 경계 체크를 피하기 위해 1로 패딩된 2D 배열 생성
        val arr = Array(R + 2) {IntArray(C + 2)}

        for (i in 0 until R + 2){
            for (j in 0 until C + 2){
                if (i == 0 || i == R + 1 || j == 0 || j == C + 1){
                    arr[i][j] = 1 // 경계 1로 채우기
                }
            }
        }
//        // 테스트 출력
//        for (i in 0 until R + 2) {
//            for (j in 0 until C + 2){
//                print("${arr[i][j]}")
//            }
//            println()
//        }

        //초기 위치와 방향
        var (ci, cj, dr) = arrayOf(1, 1, 0)

        for (n in 1 until K){
            arr[ci][cj] = n // 좌석 번호 배정
            val ni = ci + di[dr]
            val nj = cj + dj[dr]
            if (arr[ni][nj] == 0){ // 다음 셀 비어있으면 이동
                ci = ni
                cj = nj
            } else { // 그렇지 않으면 방향 변경
                dr = (dr + 1) % 4
                ci += di[dr]
                cj += dj[dr]
            }
        }
        println("$cj $ci") //최종 좌표 출력

    }
}
