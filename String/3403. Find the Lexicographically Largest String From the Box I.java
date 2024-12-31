class Solution {
    public String answerString(String word, int numFriends) {
        // If there is only one friend, no need to remove any characters.
        // Return the original word since it's the only valid result.
        if (numFriends == 1) {
            return word;
        }

        // Get the length of the input word
        int n = word.length();

        // Calculate the length of the substring to keep after removing (numFriends - 1) characters
        int m = n - numFriends + 1; // it is the max length a substring can have

        // Initialize variables to store the largest substring and the current substring being checked
        String res = ""; // The largest substring found so far
        String cur = ""; // The current substring being checked

        // Iterate through all possible starting positions of substrings in the word
        for (int i = 0; i < n; ++i) {
            // Extract a substring starting at index 'i' and ending at 'i + m' or the end of the word (whichever comes first)
            cur = word.substring(i, Math.min(i + m, n));

            // Compare the current substring with the largest substring found so far
            // If the current substring is lexicographically larger, update 'res' to this substring
            if (res.compareTo(cur) < 0) {
                res = cur;
            }
        }

        // After the loop, 'res' contains the lexicographically largest substring of the required length
        return res;
    }
}
