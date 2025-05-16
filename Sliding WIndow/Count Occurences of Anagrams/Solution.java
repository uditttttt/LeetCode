/*

🚀 Tip to Remember
"If problem says: Find/count substrings of length K that are...
(anagram, permutation, valid, balanced), use: Sliding Window + HashMap"
*/

// User function Template for Java

// Two strings are called anagrams if they contain the same characters in the 
// same frequency, just in a different order.(anagrams have same length as given string)

class Solution {

    int search(String pat, String txt) {
        // Step 1: Create frequency map of characters in 'pat'
        HashMap<Character , Integer> map = new HashMap<>();
        for(int i=0; i<pat.length(); i++){
            char c = pat.charAt(i);
            map.putIfAbsent(c , 0);                // If char is not present in map, initialize its count to 0
            map.put(c , map.get(c)+1);             // Increase the frequency of the current character
        }

        int i = 0;                                 // Left pointer of sliding window
        int j = 0;                                 // Right pointer of sliding window
        int ans = 0;                               // To count how many anagrams are found
        int count = map.size();                    // Tracks how many characters' frequencies are yet to be matched completely

        // Step 2: Start sliding the window across txt
        while(j < txt.length()){
            char c = txt.charAt(j);                // Get current character from txt

            // Step 2.1: If this char is part of the pattern, reduce its count in the map
            if(map.containsKey(c)){
                map.put(c , map.get(c)-1);         // Reduce its required frequency
                if(map.get(c) == 0) count--;       // If frequency becomes 0, this char is fully matched
            }

            // Step 2.2: When window size equals length of pattern
            if(j - i + 1 == pat.length()){
                if(count == 0){                    // If all characters matched, we found an anagram
                    ans++;
                }

                // Step 2.3: Before sliding the window, remove the effect of the leftmost character
                char c2 = txt.charAt(i);           // Char that will leave the window
                if(map.containsKey(c2)){ 
                    map.put(c2 , map.get(c2)+1);   // Add its frequency back (since it's no longer in the window)
                    if(map.get(c2) == 1) count++;  // If its frequency was zero and now became non-zero, we lost a full match
                }

                i++;                               // Move left pointer forward
            }

            j++;                                   // Move right pointer forward
        }

        return ans;                                // Return the total number of anagram substrings found
    }
}


/*

🧠 Step-by-Step Thinking in Exam
✅ Step 1: What are you trying to find?
You need to find substrings of a certain length (length = pat.length()).

You're not searching for one specific match but all matches of a condition.

🧭 This is your first clue:

You're checking every substring of size k ➝ use Sliding Window (Fixed Size Window).

✅ Step 2: What condition are you checking in each window?
Is the current window an anagram of pat?

How to check that?

You need to compare frequency of characters in current window with pat.

✅ Step 3: Why not use brute force?
You could do:

Loop through each substring of size k

Count character frequency using a map

Compare it with pattern's map

But this would be O(n × k) ➝ inefficient for large inputs.

🔄 So, instead:

You reuse the previous window’s frequency.

Just add the new char and remove the old char ➝ This is what sliding window does best!


🧠 Step-by-Step Thought Process in the Exam
Let’s simulate your brain in the exam 👇

✅ Step 1: Understand the input format
Given a pattern string (pat)

Given a text string (txt)

Need to count how many substrings of length pat.length() in txt are anagrams of pat

✅ Step 2: Spot important keywords
Substrings of fixed length → Use Sliding Window

Need to check if substring is an anagram → Compare character frequency

⏳ That gives you the direction to use:

Sliding Window + Frequency Map

✅ Step 3: Sliding Window Template
When you see:

Fixed-size window

Character-based matching

"Same frequency" logic

✅ → This is a Sliding Window + HashMap pattern
This is the same logic used in anagram or permutation-in-string problems.

🔁 Build the Approach in Steps
🔧 Step 4: Create a frequency map of pat
java
Copy
Edit
HashMap<Character, Integer> map = new HashMap<>();
for (char c : pat.toCharArray()) {
    map.put(c, map.getOrDefault(c, 0) + 1);
}
🧠 This stores how many times each character must appear.

🔧 Step 5: Use a sliding window of size pat.length()
Start with two pointers: i = 0, j = 0.

Track:

count = map.size() → means how many unique chars still need to be matched.

🔁 Expand window
When adding new j character to the window:

If it's in map → reduce its frequency

If its frequency hits 0 → that character is fully matched → decrease count

java
Copy
Edit
if(map.containsKey(txt.charAt(j))) {
   map.put(c, map.get(c) - 1);
   if(map.get(c) == 0) count--;
}
✅ Check for Anagram
If window size is pat.length():

If count == 0 → All characters matched → valid anagram found ✅

Add to answer

🔁 Shrink window from the left
Before shrinking the window, undo the effect of i-th character:

java
Copy
Edit
if(map.containsKey(txt.charAt(i))) {
   map.put(c2, map.get(c2) + 1);
   if(map.get(c2) == 1) count++;
}
Then slide window: i++


*/