class Solution {
    fun mostCommonWord(paragraph: String, banned: Array<String>): String {
        // 각 단어별 개수가 저장할 키-값 맵
        val counts: MutableMap<String,Int> = mutableMapOf()

        // 전처리 작업 후 단어 목록 배열로 저장
        val words = paragraph.replace("\\W+".toRegex()," ").toLowerCase().trim().split(" ")

        for (w in words) {
            // 금지된 단어가 아닌 경우 개수 처리
            if(!banned.contains(w)) {
                // 존재X 단어라면 기본값을 0으로 지정
                // 추출한 값에 +1 하여 저장
                counts[w] = counts.getOrDefault(w, 0) + 1
            }
        }
        return counts.maxBy { it.value }!!.key
    }
}
