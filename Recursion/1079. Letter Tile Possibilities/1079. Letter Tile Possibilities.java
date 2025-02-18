// import java.util.HashSet;

// class Solution {
//     public int numTilePossibilities(String tiles) {
//         // A HashSet to store unique sequences
//         HashSet<String> set = new HashSet<>();

//         // Boolean array to track used characters in the recursion process
//         boolean[] used = new boolean[tiles.length()];

//         // Start backtracking to generate all possible sequences
//         backtrack(tiles, new StringBuilder(), used, set);

//         // Return the count of unique sequences
//         return set.size();
//     }

//     private void backtrack(String tiles, StringBuilder sb, boolean[] used, HashSet<String> set) {
//         // If the current sequence is non-empty, add it to the set
//         if (sb.length() > 0) {
//             set.add(sb.toString());
//         }

//         // Iterate through each character in the tiles string
//         for (int i = 0; i < tiles.length(); i++) {
//             // Skip the character if it is already used in this sequence
//             if (used[i]) continue;

//             // Mark the character as used
//             used[i] = true;

//             // Add the character to the current sequence
//             sb.append(tiles.charAt(i));

//             // Recursively explore further possibilities with the new sequence
//             backtrack(tiles, sb, used, set);

//             // Backtracking step: Remove the last character to explore new possibilities
//             sb.deleteCharAt(sb.length() - 1);

//             // Mark the character as unused so it can be used in another sequence
//             used[i] = false;
//         }
//     }
// }


class Solution {
  public int numTilePossibilities(String tiles) {
      // Step 1: Create a frequency array to count occurrences of each letter (A-Z)
      int freq[] = new int[26];

      // Step 2: Populate the frequency array by iterating through the tiles string
      for (int i = 0; i < tiles.length(); i++) {
          // Increment the count for the current character in the frequency array
          // tiles.charAt(i) - 'A' converts the character to an index (0 for 'A', 1 for 'B', etc.)
          freq[tiles.charAt(i) - 'A']++;
      }

      // Step 3: Start the recursive backtracking process to count all possible sequences
      int count = backtrack(freq);

      // Step 4: Return the total count of sequences
      return count;
  }

  public int backtrack(int freq[]) {
      // Step 1: Initialize the count of sequences for the current recursive call
      int count = 0;

      // Step 2: Iterate through all 26 letters (A-Z)
      for (int i = 0; i < 26; i++) {
          // Step 3: If the current letter has no remaining occurrences, skip it
          if (freq[i] == 0) continue;

          // Step 4: Count the current letter as a valid sequence (e.g., "A", "B", etc.)
          count++;

          // Step 5: Use one occurrence of the current letter (reduce its count in the frequency array)
          freq[i]--;

          // Step 6: Recursively call backtrack to explore further sequences with the remaining letters
          // Add the result of the recursive call to the current count
          count += backtrack(freq);

          // Step 7: Backtrack (restore the count of the current letter to allow it to be used in other sequences)
          freq[i]++;
      }

      // Step 8: Return the total count of sequences generated in this recursive call
      return count;
  }
}