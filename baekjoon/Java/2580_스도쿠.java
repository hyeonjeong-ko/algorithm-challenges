/*
- **Difficulty**: Hard
- **Time Taken**: 4 Hr
- **Answer Rate**: 27.034%
Analysis
**Input**:
- A 9x9 grid representing the Sudoku board with some cells filled (1-9) and some empty (0)
**Implementation**
- **Data Structures**:
  - A 9x9 integer array `board` to store the current state of the Sudoku board
  - An `ArrayList<Dot>` to store the coordinates of empty cells
- **Algorithms**:
  - **Backtracking**:
    - For each empty cell, try placing numbers 1-9
    - Check if the number placement is valid using `isValid(row, col, num)`
    - If valid, place the number and proceed to the next cell (`dfs(depth + 1)`)
    - If a placement leads to a solution, return true to indicate success
    - If no number leads to a solution, reset the cell and backtrack (set it to 0)
  - **Validation Function** `isValid(row, col, num)`:
    - Check the current row for duplicates
    - Check the current column for duplicates
    - Check the 3x3 subgrid for duplicates

    주의!
    * 재귀 호출이 성공했는지 여부를 확인하지않고 다음 숫자들을 모두 시도하는것은 불필요하다.
    * (올바른 해를 찾았는데도 백트래킹이 계속되면 안된다. 백트래킹 후 경로를 찾으면 종료한다.)
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] rowVisited = new boolean[9][10]; //해당 row 해당 index number 가 등장했는지 확인
    static boolean[][] colVisited = new boolean[9][10];
    static boolean[][] boxVisited =  new boolean[9][10];
    static List<Dot> emptyCells = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 스도쿠 판 입력 받기
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    emptyCells.add(new Dot(i,j));
                } else {
                    int num = board[i][j];
                    rowVisited[i][num] = true;
                    colVisited[j][num] = true;
                    boxVisited[(i / 3) * 3 + (j / 3)][num] = true;
                }
            }
        }

        dfs(0);

        // 결과 출력
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(board[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean dfs(int index) {
        if (index == emptyCells.size()) {
            return true;
        }

        Dot d = emptyCells.get(index);
        int row = d.x;
        int col = d.y;

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) { // 유효성 검사
                board[row][col] = num;
                rowVisited[row][num] = colVisited[col][num] = boxVisited[(row / 3) * 3 + (col / 3)][num] = true; // 방문 체크

                if (dfs(index + 1)) {
                    return true;
                }

                board[row][col] = 0;
                rowVisited[row][num] = colVisited[col][num] = boxVisited[(row / 3) * 3 + (col / 3)][num] = false;
            }
        }

        return false;
    }

    public static boolean isValid(int row, int col, int num) {
        return !rowVisited[row][num] && !colVisited[col][num] && !boxVisited[(row / 3) * 3 + (col / 3)][num];
    }
}

class Dot {
    int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



/*
풀이 2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_2580_2 {
    static int[][] board = new int[9][9];
    static ArrayList<Dot> emptyCells = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 9; i++) { // 스도쿠 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0)
                    emptyCells.add(new Dot(i, j));
            }
        }

        dfs(0);

        // 결과 출력
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(board[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean dfs(int depth) {
        // 종료조건; 모든 빈 칸을 채우면 종료
        if (depth == emptyCells.size())
            return true;

        Dot dot = emptyCells.get(depth);
        int row = dot.x;
        int col = dot.y;

        // 후보군 (가지) 탐색
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) { // 유효성 검사
                board[row][col] = num; // 유효 원소 값 채우기
                if (dfs(depth + 1)) {  // 다음 단계로 진행
                    return true; // 해당 경로에서 숫자 모두 찾았을때, return true;
                }
                board[row][col] = 0; // 백트래킹
            }
        }
        return false; // 가능한 숫자 없음
    }

    public static boolean isValid(int row, int col, int num) {
        // 가로, 세로 검사
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // 3x3 그룹박스 검사
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }
        return true; // 모든 조건 만족하면 true
    }
}

class Dot {
    int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

*/
