// Greedy, Two Pointer
// 1. 뒤에서부터 남은 배달/수거 집 인덱스를 찾아 포인터(idxDel, idxPick)로 관리
// 2. 매 반복마다 max(idxDel, idxPick)까지 왕복한 거리((furthest+1)*2)를 누적
// 3. 한 번 왕복에 트럭 용량(cap) 만큼 배달,수거(process) 후 포인터를 당겨 다음 운행 반복

public class P15039_택배배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int idxDel = n - 1;
        int idxPick = n - 1;

        // 배달 또는 수거할 집이 하나라도 남아 있으면 반복
        while (idxDel >= 0 && idxPick >= 0) {
            // 아직 처리할 배달이 남은 가장 먼집 찾기
            while (idxDel >= 0 && deliveries[idxDel] == 0) {
                idxDel--;
            }
            // 아직 처리할 수거가 남은 가장 먼집 찾기
            while (idxPick >= 0 && pickups[idxPick] == 0) {
                idxPick--;
            }

            // 이번 운행에서 갈 가장 먼 집
            long furthest = Math.max(idxDel, idxPick);
            answer += (furthest + 1) * 2;

            // 배달과 수거를 각각 처리
            idxDel = process(deliveries, idxDel, cap);
            idxPick = process(pickups, idxPick, cap);
        }
        return answer;
    }

    private int process(int[] arr, int idx, int cap) {
        int remain = cap;
        while (idx >= 0 && remain > 0) {
            if (arr[idx] > 0) {
                int used = Math.min(arr[idx], remain);
                arr[idx] -= used;
                remain -= used;
            } else {
                idx--;
            }
        }
        return idx;
    }
}
