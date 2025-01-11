class Solution {
    public boolean canConstruct(String s, int k) {
        // If the number of required palindromes is greater than the length of the string, it's impossible
        if (k > s.length()) return false;

        // Create a frequency map for characters in the string
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Count characters with an odd frequency
        int cnt = 0;
        for (int freq : map.values()) {
            if (freq % 2 != 0) cnt++;
        }

        // A string can be partitioned into k palindromes if the number of characters with odd frequency
        // is less than or equal to k (each odd frequency contributes at least one palindrome).
        return cnt <= k;
    }
}
