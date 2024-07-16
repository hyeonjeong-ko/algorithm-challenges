class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        // 애너그램 결과 보관
        val results:MutableMap<String,MutableList<String>> = mutableMapOf();

        // 입력값 문자열 배열 순회
        for (s in strs) {
            // 문자열 정렬
            val key = s.toCharArray().sorted().joinToString("")
            // 기존에 없던 키라면 빈 리스트 삽입
            results.getOrPut(key){ mutableListOf() }
            // 키에 해당하는 리스트에 추가
            results[key]!!.add(s)
        }
        return ArrayList<List<String>>(results.values)
    }
}
