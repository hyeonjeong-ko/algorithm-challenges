import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17_폰백트래킹 {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        if (digits == null | digits.length() == 0)
            return res;

        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");

        backtrack(res, digits, digitToLetters, 0, "");

        return res;
    }

    private void backtrack(List<String> res, String digits,
                           Map<Character, String> digitToLetters,int idx, String comb) {
        if (idx == digits.length()) {
            res.add(comb);
            return;
        }

        char currentDigit = digits.charAt(idx); // 현재 숫자에 대응하는 문자열 가져오기
        String letters = digitToLetters.get(currentDigit);

        for (int i = 0; i < letters.length(); i++) {
            backtrack(res, digits, digitToLetters, idx + 1, comb + letters.charAt(i));
        }

    }
}
