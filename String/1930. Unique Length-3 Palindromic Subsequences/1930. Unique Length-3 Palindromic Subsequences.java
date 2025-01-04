class Solution {
    public int countPalindromicSubsequence(String s) {
        // Step 1: Create a HashSet to store all unique characters in the string.
        HashSet<Character> set1 = new HashSet<>();
        // Add each character of the string to the HashSet.
        // This helps in identifying the potential start and end characters of palindromic subsequences.
        for (char c : s.toCharArray()) {
            set1.add(c);
        }

        int count = 0; // Initialize a counter to track the total number of palindromic subsequences.

        // Step 2: Iterate through each unique character in the set.
        for (char c : set1) {
            // Create a second HashSet to store unique characters between the first and last occurrence of `c`.
            HashSet<Character> set2 = new HashSet<>();
            // Find the first occurrence of `c` in the string.
            int st = s.indexOf(c);
            // Find the last occurrence of `c` in the string.
            int e = s.lastIndexOf(c);

            // If `st` and `e` are the same, it means `c` appears only once.
            // Hence, it cannot form a valid palindromic subsequence. Skip to the next character.
            if (st == e) continue;

            // Step 3: Collect all characters between the first and last occurrence of `c`.
            for (int i = st + 1; i < e; i++) {
                set2.add(s.charAt(i)); // Add the middle characters to `set2`.
            }

            // Step 4: Add the size of `set2` to the total count.
            // This is because each unique character in `set2` forms a valid palindromic subsequence `c_b_c`.
            count += set2.size();
        }

        // Step 5: Return the total count of palindromic subsequences.
        return count;
    }
}

