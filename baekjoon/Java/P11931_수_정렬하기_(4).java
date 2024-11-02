import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class P11931 {
	public static void main(String[] args) throws IOException { // 내림차순 수 정렬
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			arr.add(tmp);
		}
		arr.sort(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int temp = arr.get(i);
			bw.write(temp+"\n");
		}
		bw.flush();

	}
}
