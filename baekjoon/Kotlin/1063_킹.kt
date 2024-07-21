"""
A solution to a chess piece movement simulation.

Metadata
- **Difficulty**: Medium
- **Time Taken**: 45 mins
- **Answer Rate**: 42.240%

Analysis
**Input**:
- Three strings from user input: `K`, `S`, and `N`
  - `K`: Current position of the King on a chessboard (e.g., "A1")
  - `S`: Current position of a Stone on a chessboard (e.g., "B2")
  - `N`: Number of moves to process

**Expected Time Complexity**: O(N)

**Implementation**
- **Data Structures**: 
  - Dictionary (Pair) for direction mappings
- **Algorithms**: 
  - Mapping character-based chess positions to integer indices

**Result**
- **Time Complexity**: O(N)

More
- **Key insights**: 
  - Convert chess board positions to numerical indices for easier manipulation.
"""
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (kingPos, stonePos, N) = br.readLine().split(" ")
    val moveCount = N.toInt()

    // 초기 위치 설정
    var (kingRow, kingCol) = toPos(kingPos)
    var (stoneRow, stoneCol) = toPos(stonePos)

    // 방향 맵핑 설정
    val directions = mapOf(
        "R" to Pair(0, 1),
        "L" to Pair(0, -1),
        "B" to Pair(-1, 0),
        "T" to Pair(1, 0),
        "RT" to Pair(1, 1),
        "LT" to Pair(1, -1),
        "RB" to Pair(-1, 1),
        "LB" to Pair(-1, -1)
    )

    repeat(moveCount) {
        val move = br.readLine()
        val (dx, dy) = directions[move]!!

        val newKingRow = kingRow + dx
        val newKingCol = kingCol + dy

        // 체스판 범위 체크
        if (isValidPosition(newKingRow, newKingCol)) {
            if (newKingRow == stoneRow && newKingCol == stoneCol) {
                val newStoneRow = stoneRow + dx
                val newStoneCol = stoneCol + dy
              
                if (isValidPosition(newStoneRow, newStoneCol)) { //돌 범위체크
                    stoneRow = newStoneRow
                    stoneCol = newStoneCol
                    kingRow = newKingRow
                    kingCol = newKingCol
                }
            } else {
                kingRow = newKingRow
                kingCol = newKingCol
            }
        }
    }
    println(toAB(kingRow, kingCol))
    println(toAB(stoneRow, stoneCol))
}

fun toPos(position: String): Pair<Int, Int> {
    val row = position[1] - '0'
    val col = position[0] - 'A' + 1
    return Pair(row, col)
}

fun toAB(row: Int, col: Int): String {
    val letter = 'A' + (col - 1)
    return "$letter$row"
}

fun isValidPosition(row: Int, col: Int): Boolean {
    return row in 1..8 && col in 1..8
}
