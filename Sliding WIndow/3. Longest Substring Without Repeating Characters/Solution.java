/*

🏁 Final Tip:
Practice sliding window patterns often.

Whenever you see:
✅ Substrings
✅ "without repeating" or "at most K times"
Think: Sliding Window + HashMap
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0; // left pointer of the window
        int j = 0; // right pointer of the window
        int max = 0; // stores the length of the longest valid substring found so far

        int n = s.length(); // length of the input string
        HashMap<Character, Integer> map = new HashMap<>(); // map to store characters and their counts in current window

        while(j < n) { // loop through each character using the right pointer
            char c = s.charAt(j); // get the current character

            // Add/update the count of character c in the map
            map.put(c, map.getOrDefault(c, 0) + 1);

            // Case 1: All characters in the current window are unique
            if(map.size() == j - i + 1) {
                max = Math.max(j - i + 1, max); // update max if current window size is greater
            }

            // Case 2: Duplicate character found (map size < window size)
            else if(map.size() < j - i + 1) {
                // Shrink the window from the left until all characters are unique again
                while(map.size() < j - i + 1) {
                    char c2 = s.charAt(i); // get the leftmost character
                    map.put(c2, map.get(c2) - 1); // reduce its count in the map

                    // If its count becomes 0, remove it from the map
                    if(map.get(c2) == 0) {
                        map.remove(c2);
                    }

                    i++; // move the left pointer forward to shrink the window
                }
            }

            j++; // move the right pointer to expand the window
        }

        return max; // return the length of the longest unique-character substring
    }
}
/*
🔍 Intuition & Thought Process
You're given a string s, and you need to find the length of the longest substring with all unique characters.

Instead of checking every possible substring (which is slow), use the sliding window technique:

Use two pointers i (left) and j (right) to form a dynamic window.

Use a HashMap to keep track of characters in the window and their counts.

Expand the window by moving j.

If a duplicate character is found (i.e., window has more characters than unique ones), shrink the window by moving i.

While expanding and shrinking, keep track of the maximum window size where all characters are unique.

🧠 How to Think in Exam:
Ask yourself:

What am I tracking?
→ A window (substring) of unique characters.

How do I expand and shrink this window?
→ Expand using j, shrink using i.

How do I know if the substring is valid (no duplicates)?
→ When map.size() == window size.

How to fix a window with duplicates?
→ Move i to remove duplicate characters from the window.
*/

// more optimised

// class Solution {
//     public int lengthOfLongestSubstring(String s) {
//         // Initialize two pointers (left and right) and a variable to track the maximum length
//         int l = 0;
//         int r = 0;
//         int maxLen = 0;
        
//         // Create a HashMap to store the last index of each character seen in the string
//         HashMap<Character, Integer> map = new HashMap<>();
        
//         // Expand the window by moving the right pointer
//         while (r < s.length()) {
//             // Check if the current character is already in the map
//             if (map.containsKey(s.charAt(r))) {
//                 // If the character's last position is within the current window,
//                 // move the left pointer to exclude the previous occurrence of this character
//                 if (map.get(s.charAt(r)) >= l) {
//                     l = map.get(s.charAt(r)) + 1;
//                 }
//             }
            
//             // Update the maximum length if the current window is longer
//             maxLen = Math.max(maxLen, r - l + 1);
            
//             // Update the map with the current character's index
//             map.put(s.charAt(r), r);
            
//             // Move the right pointer to expand the window
//             r++;
//         }
        
//         // Return the maximum length of the substring with all unique characters
//         return maxLen;
//     }
// }
