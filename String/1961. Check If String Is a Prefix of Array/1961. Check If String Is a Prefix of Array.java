class Solution {
    public boolean isPrefixString(String s, String[] words) {
        // Create a StringBuilder to build the concatenated string
        StringBuilder sb = new StringBuilder();

        // Get the number of words in the array
        int n = words.length;

        // Iterate through the array of words
        for (int i = 0; i < n; i++) {
            // Append the current word to the StringBuilder
            sb.append(words[i]);

            // Check if the length of sb is still less than s
            if (sb.length() < s.length()) {
                continue; // Keep adding words if sb is shorter than s
            } 
            // Check if the concatenated string matches s
            else if (sb.toString().equals(s)) {
                return true; // If equal, s is a prefix string
            } 
            // If sb becomes longer than s, it's not a prefix
            else if (sb.length() > s.length()) {
                return false; // Early exit since s can't be formed
            }
        }
        // If we finish the loop without finding a match, return false
        return false;
    }
}
