/*
* Algorithm; Sliding-Window,Two Pointers
* Time Complexity; O(n)
* 두 포인터로 부분 수열 합을 추적
* currentSum < k => r++, currentSum > k => l++, currentSum == k => 가장짧으면 결과갱신
*/

class Solution {
    public int[] solution(int[] sequence, int k) {
        int l = 0,r = 0;
        int currentSum = sequence[0]; // 부분수열 합
        int minLength = Integer.MAX_VALUE; //최소길이 체크
        int[] res = new int[2];
        
        while (r < sequence.length) {
            if (currentSum == k) {
                if (r - l < minLength) { // min 갱신
                    minLength = r - l;
                    res[0] = l;
                    res[1] = r;
                }
                currentSum -= sequence[l];
                l++;
            } else if (currentSum < k) {
                r++;
                if (r < sequence.length) {
                    currentSum += sequence[r]; //새 값 포함
                }
            } else { // currentSum > k
                currentSum -= sequence[l]; // 현재값 제외
                l++;
            }
        }
        return res;
    }
}
