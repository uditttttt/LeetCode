import java.util.*;

class Solution {
    // Inner class to represent a word and the number of steps taken to reach it
    class Pair {
        String w;  // The current word
        int steps; // Number of steps taken to reach this word

        public Pair(String words, int steps) {
            this.w = words;
            this.steps = steps;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList into a HashSet for O(1) lookup time
        HashSet<String> set = new HashSet<>();
        int n = wordList.size();
        for (int i = 0; i < n; i++) {
            set.add(wordList.get(i));  // Add all words from wordList to the set
        }

        // BFS queue to store words along with the transformation steps
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 1)); // Start with beginWord and step count 1

        // Remove beginWord from the set to avoid revisiting it
        set.remove(beginWord);

        // Start BFS
        while (!q.isEmpty()) {
            Pair curr = q.poll(); // Get the front pair from the queue
            String word = curr.w; // Current word being processed
            int steps = curr.steps; // Steps taken to reach this word

            // If we reach the endWord, return the number of steps
            if (word.equals(endWord)) return steps;

            // Try changing each letter of the word to find valid transformations
            for (int i = 0; i < word.length(); i++) { // Iterate through each character position
                for (char c = 'a'; c <= 'z'; c++) { // Try replacing it with 'a' to 'z'
                    char[] replacedCharArray = word.toCharArray(); // Convert word to character array
                    replacedCharArray[i] = c; // Replace character at position i
                    String replacedWord = new String(replacedCharArray); // Convert back to string

                    // If the transformed word exists in the set, it's a valid transformation
                    if (set.contains(replacedWord)) {
                        set.remove(replacedWord); // Remove from the set to avoid revisiting
                        q.offer(new Pair(replacedWord, steps + 1)); // Add to queue with incremented step count
                    }
                }
            }
        }

        // If no transformation sequence found, return 0
        return 0;
    }
}

/*

Intuition Behind the Algorithm (BFS Approach)
Graph Representation:

Each word is a node.
A transformation (changing one letter) creates an edge between two nodes.
Why BFS?

BFS finds the shortest path in an unweighted graph.
It ensures we reach the endWord in the minimum number of transformations.
Steps in the Algorithm:

Convert wordList into a HashSet for quick lookups.
Use a queue (BFS) to explore transformations level by level.
For each word, try changing one letter at a time to form a new word.
If the new word is in the wordList, add it to the queue and remove it from the set.
Stop if we reach endWord, otherwise continue BFS.
If the queue gets empty without finding endWord, return 0.
Time Complexity Analysis
Word length = L, Number of words = N
Each word transformation has L choices (each character) and 26 possibilities (a-z).
Worst case: O(N × L × 26) ≈ O(N × L), which is efficient for reasonable input sizes.
This approach ensures we find the shortest transformation sequence efficiently! 🚀







*/
