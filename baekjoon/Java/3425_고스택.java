import java.io.*;
import java.util.*;
public class PS_01 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> ans = new ArrayList<String>();

		while (true) {
			ArrayList<String> operations = new ArrayList<String>();
			
			// 새로운 문제 입력받기
			while(true) {
				String line = br.readLine();
				if (line.equals("QUIT")) {
					/*답안 출력*/ 
					printResult(ans);
					return; // 프로그램 종료
				}
				if (line.equals("END")) break; //  입력받기 끝
				operations.add(line);
			}
			for(String cmd : operations) System.out.println(cmd);
			
			int N = Integer.parseInt(br.readLine().trim());
			
			for(int i = 0; i < N; i++) {
				int startValue = Integer.parseInt(br.readLine());
				ans.add(performOperation(operations,startValue));	
			}
		}

		
	}
	public static void printResult(ArrayList<String> ans) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (String line : ans) {
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
	}
	
	public static String performOperation(ArrayList<String> operations, int startValue) {
		Stack<Long> st = new Stack<>();
		st.add((long) startValue);
		
		for (String op : operations) {
			if (op.startsWith("NUM")) {
				long x = Long.parseLong(op.split(" ")[1]);
				st.push(x);
			} else if (op.startsWith("POP")) {
				if (st.isEmpty()) return "ERROR";
				st.pop();
			} else if (op.startsWith("INV")) {
				if (st.isEmpty()) return "ERROR";
				st.push(-st.pop());
			} else if (op.startsWith("DUP")) {
				st.push(st.peek());
			} else if (op.startsWith("SWP")) {
				if (st.size() < 2) return "ERROR";
				long first = st.pop();
				long second = st.pop();
				st.push(first);
				st.push(second);
			} else if (op.startsWith("ADD")) {
				if (st.size() < 2) return "ERROR";

				long first = st.pop();
				long second = st.pop();
				st.push(first + second);
			} else if (op.startsWith("SUB")) {
				if (st.size() < 2) return "ERROR";

				long first = st.pop();
				long second = st.pop();
				st.push(first - second);
			} else if (op.startsWith("MUL")) {
				if (st.size() < 2) return "ERROR";

				long first = st.pop();
				long second = st.pop();
				st.push(first*second);
			} else if (op.startsWith("DIV")) {
				if (st.size() < 2) return "ERROR";

				long first = st.pop();
				long second = st.pop();
				if (first == 0) return "ERROR"; // 0으로 나눴으면 에러
				
				long result = Math.abs(second) / Math.abs(first);
				// 몫의 부호 결정;
				// 피연산자중 음수가 한 개일때는 몫의 부호가 음수
				if ((first < 0) ^ (second < 0)) result = -result;
				st.push(result);
			} else if (op.startsWith("MOD")) {
				if (st.size() < 2) return "ERROR";

				long first = st.pop();
				long second = st.pop();
				if (first == 0) return "ERROR"; // 0으로 나눴으면 에러
				long result = Math.abs(second) % Math.abs(first);
				//  나머지의 부호는 피제수의 부호와 같다.
				if (second < 0) result = -result;
				st.push(result);
			}
		}
		if (!(st.size() != 1)) { // 스택에 저장되어 있는 숫자 1 X
			return "ERROR";
		}
		
		return  String.valueOf(st.pop());
		
	}

}
