import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    private static final Map<Character, int[]> udrl = new HashMap<>();
    
    public int solution(String dirs) {
        Set<String> sets = new HashSet<>();
        int x = 0, y = 0;
        
        udrl.put('U', new int[] {0, -1});
        udrl.put('D', new int[] {0, 1});
        udrl.put('L', new int[] {-1, 0});
        udrl.put('R', new int[] {1, 0});
        
        for (char d : dirs.toCharArray()) {
            int dx = udrl.get(d)[0];
            int dy = udrl.get(d)[1];
            int nx = x + dx;
            int ny = y + dy;
            
            if (ny >= -5 && ny <= 5 && nx >= -5 && nx <= 5) {
                sets.add(x + "," + y + "," + nx + "," + ny);
                sets.add(nx + "," + ny + "," + x + "," + y);
                x = nx;
                y = ny;
            }
        }
        
        return sets.size() / 2;
    }
}
