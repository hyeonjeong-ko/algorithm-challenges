/*
A solution to calculate the area formed by columns using their heights.
Metadata
- **Difficulty**: Medium
- **Time Taken**: 1 Hr
Analysis
**Input**:
- A list of pairs `(L, H)`, where `L` is the position of a column and `H` is its height
**Expected Time Complexity**:  1<= L,H <=1,000
**Implementation**
- **Data Structures**: 
  - An array `lst` of size 1001 to store the height of columns at respective positions
- **Algorithms**:
  - Two-pass traversal to calculate the area:
    1. From the left up to the tallest column
    2. From the right up to the tallest column
*/
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val N = reader.readLine().toInt()

    val lst = IntArray(1001)

    var mx_i = 0
    var mx = 0

    repeat(N) {
        val (L, H) = reader.readLine().split(" ").map { it.toInt() }
        lst[L] = H

        if (mx < H) {
            mx_i = L
            mx = H
        }
    }

    var ans = 0
    mx = 0
    for (i in 0..mx_i) {
        mx = maxOf(mx, lst[i])
        ans += mx
    }

    mx = 0
    for (i in 1000 downTo mx_i + 1) {
        mx = maxOf(mx, lst[i])
        ans += mx
    }
    println(ans)
}
