class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 애너그램 결과 보관
        Map<String, List<String>> results = new HashMap<>();

        // 입력값 문자열 배열 순회
        for (String s : strs){
            // 문자열을 문자 배열로 변환
            char[] chars = s.toCharArray();
            // 문자 배열 정렬
            Arrays.sort(chars);
            // 문자 배열을 키로 하기 위해 다시 문자열로
            String key = String.valueOf(chars);

            //기존x 키라면 빈 리스트 삽입
            if(!results.containsKey(key))
                results.put(key, new ArrayList<>());
            //키에 해당하는 리스트에 추가
            results.get(key).add(s)
;        }
        return new ArrayList<>(results.values());
    }
}
