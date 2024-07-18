import java.io.*;

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()

    var result = -1

    // 5kg 봉지 최대개수부터 0까지 역순조회
    for(cnt_5 in N / 5 downTo 0) {
        // 남은 무게 3kg 봉지
        val cnt_3 = (N - (cnt_5 * 5)) / 3
        if (cnt_5 * 5 + cnt_3 * 3 == N) {
            result = cnt_5 + cnt_3
            break
        }
    }
    println(result)
}
