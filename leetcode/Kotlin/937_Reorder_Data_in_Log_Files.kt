class Solution {
    fun reorderLogFiles(logs: Array<String>): Array<String> {
        val letterList = mutableListOf<String>()
        val digitList = mutableListOf<String>()

        for (log in logs) {
            // 로그 종류 확인 후 숫자 로그면 숫자 리스트에 삽입
            if (Character.isDigit(log.split(" ")[1][0])){
                digitList.add(log)
            } else {
                // 숫자 로그가 아니라면 문자 리스트에 삽입
                letterList.add(log)
            }
        }
        // 문자 리스트 정렬
        letterList.sortWith( Comparator { s1: String, s2: String ->
            // 식별자, 식별자 외 나머지 부분으로 나뉜다.
            val s1x = s1.split(" ",limit = 2)
            val s2x = s2.split(" ",limit = 2)
            // 문자 로그 사전순 비교
            val compared = s1x[1].compareTo(s2x[1])
            //문자동일한 경우 식별자 비교해 먼저 리턴
            if (compared == 0){
                s1x[0].compareTo(s2x[0])
            } else{
                compared
            }
        })
        letterList.addAll(digitList)
        return letterList.toTypedArray() //리스트->배열
    }
}
