class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // Initialize an array to store the maximum frequency required for each character ('a' to 'z')
        int[] maxCharFreq = new int[26]; // This will hold the maximum frequency of each character in `words2`
        int[] tempCharFreq = new int[26]; // Temporary array to calculate frequencies for individual words

        // Step 1: Build `maxCharFreq` array based on words in `words2`
        for (String word : words2) {
            // Reset the temporary frequency array for the current word
            Arrays.fill(tempCharFreq, 0);
            for (char ch : word.toCharArray()) {
                // Calculate frequency of each character in the current word
                tempCharFreq[ch - 'a']++;
                // Update `maxCharFreq` to store the maximum frequency of each character required
                maxCharFreq[ch - 'a'] = Math.max(maxCharFreq[ch - 'a'], tempCharFreq[ch - 'a']);
            }
        }

        // Step 2: Initialize the result list to store universal words
        List<String> universalWords = new ArrayList<>();

        // Step 3: Check each word in `words1` against the `maxCharFreq` requirements
        for (String word : words1) {
            // Reset the temporary frequency array for the current word
            Arrays.fill(tempCharFreq, 0);
            for (char ch : word.toCharArray()) {
                // Calculate frequency of each character in the current word
                tempCharFreq[ch - 'a']++;
            }

            // Check if the current word meets the `maxCharFreq` requirements
            boolean isUniversal = true; // Assume the word is universal
            for (int i = 0; i < 26; ++i) {
                // If any character's frequency in `word` is less than the required maximum, it's not universal
                if (maxCharFreq[i] > tempCharFreq[i]) {
                    isUniversal = false;
                    break;
                }
            }

            // If the word satisfies all requirements, add it to the result list
            if (isUniversal) {
                universalWords.add(word);
            }
        }

        // Step 4: Return the list of universal words
        return universalWords;
    }
}
