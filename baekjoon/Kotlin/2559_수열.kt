import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (N, K) = br.readLine().split(" ").map { it.toInt() }

    val lst = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    var sm = lst.take(K).sum()

    var ans = sm

    for (i in K until N) {
        sm = sm + lst[i] - lst[i - K]
        ans = maxOf(ans, sm)
    }
    println(ans)
}
