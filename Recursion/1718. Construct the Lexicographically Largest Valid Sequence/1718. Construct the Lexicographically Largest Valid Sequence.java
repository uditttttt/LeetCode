class Solution {
  // Main method to construct the distanced sequence
  public int[] constructDistancedSequence(int n) {
      // Array to keep track of used numbers (1 to n)
      boolean used[] = new boolean[n + 1];
      // Array to store the resulting sequence (length = 2n - 1)
      int seq[] = new int[2 * n - 1];
      // Start the backtracking process from index 0
      backtrack(0, used, seq, n);
      // Return the constructed sequence
      return seq;
  }

  // Backtracking function to fill the sequence
  public boolean backtrack(int index, boolean used[], int seq[], int n) {
      // Skip indices that are already filled in the sequence
      while (index < seq.length && seq[index] != 0)
          index++;
      // If all indices are filled, the sequence is complete
      if (index == seq.length)
          return true;

      // Try placing numbers from n to 1 in the sequence
      for (int i = n; i >= 1; i--) {
          // Skip if the number is already used
          if (used[i])
              continue;

          // Handle the case for number 1 (only needs to be placed once)
          if (i == 1) {
              // Place 1 at the current index
              seq[index] = i;
              // Mark 1 as used
              used[i] = true;
              // Recursively try to fill the remaining sequence
              if (backtrack(index + 1, used, seq, n))
                  return true; // If successful, return true
              // If not successful, backtrack (undo the placement)
              seq[index] = 0;
              used[i] = false;
          }
          // Handle numbers greater than 1 (need to be placed twice)
          else if (index + i < seq.length && seq[index + i] == 0) {
              // Place the number at the current index
              seq[index] = i;
              // Place the same number at index + i (to maintain distance)
              seq[index + i] = i;
              // Mark the number as used
              used[i] = true;
              // Recursively try to fill the remaining sequence
              if (backtrack(index + 1, used, seq, n))
                  return true; // If successful, return true
              // If not successful, backtrack (undo the placements)
              seq[index] = 0;
              seq[index + i] = 0;
              used[i] = false;
          }
      }

      // If no number can be placed at the current index, return false
      return false;
  }
}