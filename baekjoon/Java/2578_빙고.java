"""
A solution to the bingo game problem.

Metadata
Difficulty: Intermediate
Time Taken: 1.5 hr
Correct Answer Rate: 45.724%

Analysis
Input:
- 5x5 grid for bingo numbers
- List of called numbers by the host
Expected Time Complexity: O(N), where N is the number of called numbers

Implementation
Data Structures: Arrays, Lists
Algorithms: Counting, Array Indexing
Statements: for loop, if conditions, break statements

Result
Time Complexity: O(N)

More
- Key insights: Using a position list to quickly find coordinates of called numbers; Checking bingo conditions efficiently with arrays for rows, columns, and diagonals.
"""

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[5][5];
        for (int i = 0; i < 5; i++){ // bingo 입력받기
            String[] line = bf.readLine().split(" ");
            for(int j = 0; j < 5; j++){
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            String[] line = bf.readLine().split(" ");
            for (String num : line) lst.add(Integer.parseInt(num));
        }

        int[] pos_lst = new int[26];
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                pos_lst[arr[i][j]] = i * 5 + j; // 빙고 배열 숫자 위치 저장
            }
        }

        int[][] v = new int[4][10]; // v0~v3 빈도수 체크

        for (int n : lst){
            int i = pos_lst[n] / 5;
            int j = pos_lst[n] % 5;
            v[0][j]++; // 세로 개수 누적
            v[1][i]++; // 가로 개수 누적
            v[2][i - j + 4]++; // 우측 아래 대각선 개수 누적 (오프셋 추가)
            v[3][i + j]++; // 우측 위쪽 대각선 개수 누적
            int cnt = 0;
            for (int[] tlst : v){
                for (int value : tlst) {
                    if (value == 5) cnt++;
                }
            }
            if (cnt >= 3) break; // 3개 이상이면 빙고!
        }

        int sum = 0;
        for(int value : v[0]) sum += value;
        System.out.println(sum); // 표시(누적)된 개수가 불러준 숫자의 개수
    }
}
