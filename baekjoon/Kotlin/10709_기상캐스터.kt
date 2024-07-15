import java.io.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (H,W) = reader.readLine().split(" ").map {it.toInt()}
    val arr = Array(H) {reader.readLine()}
    val v = Array(H) {IntArray(W)}

    for (i in 0 until H) {
        var cnt = -1
        for (j in 0 until W) {
            if (arr[i][j] == 'c') cnt = 0
            else {
                if (cnt >= 0) cnt++
            }
            v[i][j] = cnt
        }
    }

    for (row in v) {
        println(row.joinToString(" "))
    }
}
