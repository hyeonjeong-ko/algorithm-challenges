class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letterList = new ArrayList<>();
        List<String> digitList = new ArrayList<>();

        for (String log : logs){ // 문자, 숫자 로그 분리
            if(Character.isDigit(log.split(" ")[1].charAt(0))){
                digitList.add(log);
            } else {
                letterList.add(log);
            }
        }

        // 문자 리스트 정렬
        letterList.sort((s1,s2)->{
            String[] s1x = s1.split(" ",2); // 식별자,식별자 외 나머지 부분으로 나뉜다.
            String[] s2x = s2.split(" ",2); // ["id2", "art can"]

            // 문자 로그 사전순 비교
            int compared = s1x[1].compareTo(s2x[1]);
            // 문자 동일하면 식별자 비교
            if (compared == 0){
                return s1x[0].compareTo(s2x[0]);
            } else {
                return compared;
            }
        });

        letterList.addAll(digitList);
        return letterList.toArray(new String[0]);
    }
}
