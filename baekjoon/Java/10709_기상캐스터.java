import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String[] HW = bf.readLine().split(" ");
        int H = Integer.parseInt(HW[0]);
        int W = Integer.parseInt(HW[1]);

        String[] arr = new String[H];
        for (int i = 0; i < H; i++) arr[i] = bf.readLine();
        
        int[][] v = new int[H][W];

        // 구름 계산
        for(int i = 0; i < H; i++) {
            int cnt = -1;
            for(int j = 0; j < W; j++){
                if(arr[i].charAt(j) == 'c') {
                    cnt = 0;
                } else {
                    if (cnt >= 0) cnt++;
                }
                v[i][j] = cnt;
            }
        }

        // 결과 출력
        for (int i = 0; i < H; i++){
            for (int j = 0; j < W; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        bf.close();
    }
}
