public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 각 행의 첫 번째 요소들
        int[] eachRowFirst = new int[m];
        for (int i = 0; i < m; i++) {
            eachRowFirst[i] = matrix[i][0];
        }

        // target이 위치할 행을 찾기
        int targetRow = Arrays.binarySearch(eachRowFirst, target);
        
        // 만약 행 첫번째위치에 답이 있다면
        if (0 <= targetRow && targetRow < m && matrix[targetRow][0] == target) {
            return true;
        }

        if (targetRow < 0) { // 답이 없다면 음수 반환
            System.out.println(targetRow);
            targetRow = -(targetRow + 1) - 1; //쉽게생각..삽입위치인덱스-1==해당위치
            System.out.println(targetRow);
        }


        // 찾은 행이 행렬 범위를 벗어날 경우, False
        if (targetRow < 0 || m <= targetRow) {
            return false;
        }

        // 찾은 행에서 target을 이진 탐색
        int ans = Arrays.binarySearch(matrix[targetRow], target);
        return 0 <= ans && ans < n && matrix[targetRow][ans] == target;
    }
}
