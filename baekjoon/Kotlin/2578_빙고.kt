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

import java.io.*

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))

    // 5x5 빙고 배열 입력 받기
    val arr = Array(5) {IntArray(5)}
    for (i in 0 until 5) {
        val line = bf.readLine().split(" ").map {it.toInt()}
        for (j in 0 until 5) {
            arr[i][j] = line[j]
        }
    }
    // 사회자가 부르는 숫자 리스트 입력 받기 (1차원)
    val lst = mutableListOf<Int>()
    for (i in 0 until 5) {
        val line = bf.readLine().split(" ").map {it.toInt()}
        lst.addAll(line)
    }

    // 숫자별 위치 저장 배열 초기화
    val pos_lst = IntArray(26)
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            pos_lst[arr[i][j]] = i * 5 + j
        }
    }
    // 빙고 개수 체크 배열 초기화
    val v = Array(4) {IntArray(10)}

    // 사회자가 부르는 숫자 처리
    for (n in lst) {
        val index = pos_lst[n]
        val i = index / 5
        val j = index % 5

        v[0][j]++ // 세로 개수를 누적
        v[1][i]++ // 가로 개수를 누적
        v[2][i - j + 4]++ // 우측 아래 대각선 개수를 누적 (오프셋 추가)
        v[3][i + j]++ // 우측 위쪽 대각선 개수를 누적

        var cnt = 0
        for (tlst in v) {
            cnt += tlst.count{it == 5}
        }
        if (cnt >= 3) break
    }
    // 세로 체크된 개수합 출력
    var sum = 0
    for (value in v[0]) sum += value
    println(sum)
}

