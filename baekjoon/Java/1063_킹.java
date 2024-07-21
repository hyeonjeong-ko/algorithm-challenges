"""
A solution to a chess piece movement simulation.

Metadata
- **Difficulty**: Medium
- **Time Taken**: 45 mins
- **Answer Rate**: 42.240%

Analysis
**Input**:
- Three strings from user input: `K`, `S`, and `N`
  - `K`: Current position of the King on a chessboard (e.g., "A1")
  - `S`: Current position of a Stone on a chessboard (e.g., "B2")
  - `N`: Number of moves to process

**Expected Time Complexity**: O(N)

**Implementation**
- **Data Structures**: 
  - Dictionary (HashMap) for direction mappings
- **Algorithms**: 
  - Mapping character-based chess positions to integer indices

**Result**
- **Time Complexity**: O(N)

More
- **Key insights**: 
  - Convert chess board positions to numerical indices for easier manipulation. (ord(),chr())
"""
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        String kingPos = inputs[0];
        String stonePos = inputs[1];
        int N = Integer.parseInt(inputs[2]);

        int[] king = toPos(kingPos);
        int[] stone = toPos(stonePos);

        Map<String, int[]> directions = new HashMap<>();
        directions.put("R", new int[]{0, 1});
        directions.put("L", new int[]{0, -1});
        directions.put("B", new int[]{-1, 0});
        directions.put("T", new int[]{1, 0});
        directions.put("RT", new int[]{1, 1});
        directions.put("LT", new int[]{1, -1});
        directions.put("RB", new int[]{-1, 1});
        directions.put("LB", new int[]{-1, -1});

        for(int i = 0; i < N; i++) {
            String move = br.readLine();
            int[] direction = directions.get(move);
            int newKingRow = king[0] + direction[0];
            int newKingCol = king[1] + direction[1];
            
            // Check if the move is within bounds
            if(isValidPosition(newKingRow,newKingCol)) {
                if (newKingRow == stone[0] && newKingCol == stone[1]) {
                    int newStoneRow = stone[0] + direction[0];
                    int newStoneCol = stone[1] + direction[1];

                    // Move stone if it's within bounds
                    if (isValidPosition(newStoneRow, newStoneCol)) {
                        stone[0] = newStoneRow;
                        stone[1] = newStoneCol;
                        king[0] = newKingRow;
                        king[1] = newKingCol;
                    }
                } else {
                    king[0] = newKingRow;
                    king[1] = newKingCol;
                }
            }
        }
        System.out.println(toAB(king[0], king[1]));
        System.out.println(toAB(stone[0], stone[1]));
    }

    // "A1" -> i,j
    private static int[] toPos(String position) {
        int row = position.charAt(1) - '0';
        int col = position.charAt(0) - 'A' + 1;
        return new int[]{row, col};
    }
    // i,j -> "A1"
    private static String toAB(int row, int col) {
        char letter = (char) ('A' + col - 1);
        return "" + letter + row;
    }
    
    private static boolean isValidPosition(int row,int col) {
        return row>=1 && row <=8 && col >=1 && col <=8;
    }
}
