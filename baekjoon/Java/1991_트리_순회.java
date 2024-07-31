import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static TreeNode head = new TreeNode('A');

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int lines = Integer.parseInt(br.readLine());

        for(int i = 0; i < lines; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            insertNode(head, root, left, right);
        }

        preOrderTraversal(head);
        System.out.println();
        inOrderTraversal(head);
        System.out.println();
        postOrderTraversal(head);
    }

    // head 에서 root 를 찾아, left & right 삽입
    public static void insertNode(TreeNode curr, char root, char left, char right) {
        if (curr == null) return;
        if (curr.val == root) {
            curr.left = (left == '.')? null : new TreeNode(left);
            curr.right = (right == '.')? null : new TreeNode(right);
        } else {
            insertNode(curr.left, root, left, right);
            insertNode(curr.right, root, left, right);
        }
    }

    public static void preOrderTraversal(TreeNode root) { // 전위순회
        if (root == null) return;

        // 루트 방문
        System.out.print(root.val);

        // 왼쪽 서브트리 전위 순회
        preOrderTraversal(root.left);

        // 오른쪽 서브트리 전위 순회
        preOrderTraversal(root.right);
    }
    public static void inOrderTraversal(TreeNode root) { // 중위 순회
        if (root == null) return;

        // 왼쪽 서브트리 중위 순회
        inOrderTraversal(root.left);

        // 루트 방문
        System.out.print(root.val);

        // 오른쪽 서브트리 중위 순회
        inOrderTraversal(root.right);

    }
    public static void postOrderTraversal(TreeNode root) { // 후위 순회
        if (root == null) return;

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);

        // 루트 방문
        System.out.print(root.val);
    }
}
class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    public TreeNode(char val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
