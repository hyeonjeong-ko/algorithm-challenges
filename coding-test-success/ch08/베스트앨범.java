// Hash,Sort

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        // 1. 장르별로 총 재생횟수, 곡 정보 저장
        Map<String, Integer> genrePlayCount = new HashMap<>(); // 장르 : 총재생횟수
        Map<String, List<int[]>> songsByGenre = new HashMap<>(); // 장르 : [재생수, 곡번호]
        
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
            
            songsByGenre.putIfAbsent(genres[i], new ArrayList<>()); // 초기화
            songsByGenre.get(genres[i]).add(new int[]{plays[i], i});
        }
        
        // 2. 장르를 총 재생 횟수 기준으로 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        sortedGenres.sort((g1,g2) -> genrePlayCount.get(g2) - genrePlayCount.get(g1));
        
        // 3. 베스트 앨범 구성
        List<Integer> bestAlbum = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<int[]> songs = songsByGenre.get(genre);
            
            // 장르 내 곡 정렬: 재생 횟수 내림차순, 고유 번호 오름차순
            songs.sort((s1,s2) -> s1[0] == s2[0] ? s1[1] - s2[1] : s2[0] - s1[0]);
            
            // 상위 2곡 추가
            for (int i = 0; i < Math.min(2, songs.size()); i++)
                bestAlbum.add(songs.get(i)[1]);
        }
        return bestAlbum.stream().mapToInt(i -> i).toArray();
    }
}
