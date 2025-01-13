class Solution {
    public int minimumLength(String s) {
        // Step 1: Get the length of the string
        int n = s.length();
        
        // Step 2: Create a frequency array to count occurrences of each character
        // There are 26 lowercase English letters, so the array size is 26
        int[] freq = new int[26];

        // Step 3: Populate the frequency array
        for (int i = 0; i < s.length(); i++) { // Loop through each character in the string
            char ch = s.charAt(i);            // Get the current character
            freq[ch - 'a']++;                 // Increment the count for this character
            // (ch - 'a') maps 'a' to index 0, 'b' to index 1, ..., 'z' to index 25
        }

        // Step 4: Iterate over the frequency array to apply the reduction rule
        for (int i = 0; i < 26; i++) {       // Check for each character from 'a' to 'z'
            while (freq[i] >= 3) {           // If the frequency of this character is 3 or more
                n = n - 2;                   // Reduce the length of the string by 2
                freq[i] = freq[i] - 2;       // Reduce the frequency of this character by 2
            }
        }

        // Step 5: Return the remaining length of the string
        return n;
    }
}
