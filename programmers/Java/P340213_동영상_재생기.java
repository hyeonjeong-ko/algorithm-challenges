/*
구현 문제.

*문제 핵심 정리
명령어 처리 로직
- 각 명령어를 차례대로 수행하며 위치를 업데이트한다.

경계 조건 처리:
- 10초 이전 위치로 이동할 때는 0초로 설정.
- 동영상의 길이를 넘어서면 마지막 위치로 설정.
- 오프닝 구간에 있을 때 오프닝 끝 위치로 자동 이동.

시간 변환 함수
- "mm"를 초로 변환하는 함수와 초를 "mm" 형식으로 다시 변환하는 함수가 필요
*/

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
       // 시간(str) -> 초(int) 단위로 변환
        int videoLenSec = toSeconds(video_len);
        int posSec = toSeconds(pos);
        int opStartSec = toSeconds(op_start);
        int opEndSec = toSeconds(op_end);
        
        for (String cmd : commands) {
            // 오프닝 구간 체크 및 이동
            if(opStartSec <= posSec && posSec <= opEndSec)
                posSec = opEndSec;
            
            // 명령어 처리
            if ("prev".equals(cmd)) {
                posSec = Math.max(0, posSec - 10);
            } else if ("next".equals(cmd)) {
                posSec = Math.min(videoLenSec, posSec + 10);
            }
            
            // 오프닝 구간 체크 및 이동
            if(opStartSec <= posSec && posSec <= opEndSec)
                posSec = opEndSec;
        }
        return timeToStr(posSec);
    }
    
    // "mm:ss" 문자열 -> 초(int)
    private static int toSeconds(String timeStr){
        String[] parts = timeStr.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }
    
    // 초(int) -> "mm:ss" 형식의 문자열
    private static String timeToStr(int seconds){
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
