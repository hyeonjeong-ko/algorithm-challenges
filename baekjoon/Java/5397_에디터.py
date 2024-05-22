import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기 문자열을 왼쪽 스택에 저장
        String initial = br.readLine();
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        for (char ch : initial.toCharArray()) {
            leftStack.push(ch);
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "L":
                    if (!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                    break;
                case "D":
                    if (!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                    break;
                case "B":
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                    break;
                case "P":
                    leftStack.push(command[1].charAt(0));
                    break;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (char ch : leftStack) {
            answer.append(ch);
        }
        while (!rightStack.isEmpty()) {
            answer.append(rightStack.pop());
        }
        System.out.println(answer.toString());
    }
}
