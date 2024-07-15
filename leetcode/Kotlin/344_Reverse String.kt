class Solution {
    fun reverseString(s: CharArray): Unit {
        var start = 0
        var end = s.size - 1
        // 서로 중앙으로 이동하다 겹치면 종료
        while (start < end) {
            // also로 우아하게 스왑!
            s[start] = s[end].also { s[end] = s[start] }
            start++
            end--
        }
    }
}
