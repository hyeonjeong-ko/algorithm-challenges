// 중복 문자열 제거

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1543 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String part = br.readLine();

		int partAllLength = s1.length() - s1.replace(part,"").length();
		System.out.println(partAllLength / part.length());
	}
}
