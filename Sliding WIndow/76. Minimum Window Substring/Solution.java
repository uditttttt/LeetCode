class Solution {
    public String minWindow(String s, String t) {
        // If string s is smaller than t, no window is possible
        if(s.length() < t.length()) return "";

        // HashMap to store frequency of characters in t
        HashMap<Character , Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1); // count frequency
        }

        int i = 0; // left pointer of window
        int j = 0; // right pointer of window
        int si = 0, e = 0; // start and end of the minimum window
        int min = Integer.MAX_VALUE; // to track length of minimum window
        int count = map.size(); // number of unique characters to match

        // Start sliding the window
        while(j < s.length()){
            char c = s.charAt(j);

            // If current character is part of t, decrease its count in the map
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                // If frequency of this char becomes 0, one unique char matched
                if(map.get(c) == 0){
                    count--;
                }
            }

            // When all characters are matched
            if(count == 0){
                // Try to shrink the window from the left as we require min window size
                while(count == 0){
                    // Update the minimum window if current is smaller
                    if(j - i + 1 < min){
                        min = j - i + 1;
                        si = i; // store start index of best window
                        e = j;  // store end index of best window
                    }

                    // Try removing the leftmost character to shrink the window
                    char c1 = s.charAt(i);
                    if(map.containsKey(c1)){
                        map.put(c1, map.get(c1) + 1); // put it back into the map
                        // If frequency becomes > 0, we are missing a required character
                        if(map.get(c1) > 0) count++;
                    }
                    i++; // move left pointer
                }
            }

            // Expand window from the right
            j++;
        }

        // If no valid window found, return empty string
        return min == Integer.MAX_VALUE ? "" : s.substring(si, e + 1);
    }
}


/*
✅ Step-by-Step Thinking Strategy:
🧠 Step 1: Understand what’s being asked
Rephrase the question in your own mind:

"I have a string s and I need to find the smallest window in s that contains all characters of t (with exact count)."

This means:

It's not about order.

Duplicates matter — if t = "AABC", the window must contain 2 A’s, 1 B, 1 C.

🔍 Step 2: Recognize the core pattern
Ask:

"Does this involve a substring or subarray?"
Yes ✅ — We’re dealing with substring in a sliding window.

This is a key hint to use the Sliding Window technique.

🧠 Step 3: What do I need to track?
You need to:

Track all characters in t and their frequencies.

Move a window in s and check if the current window contains all of them.

That leads to:

Use a HashMap to store frequency of characters in t.

Two pointers: i (left) and j (right) to define the window.

🧮 Step 4: Choose Data Structures
Ask:

"What data structure helps track frequency of chars?"

Use:

java
Copy
Edit
HashMap<Character, Integer> map = new HashMap<>();
Then, for each char in t, do:

java
Copy
Edit
map.put(c, map.getOrDefault(c, 0) + 1);
🚪 Step 5: Start Sliding the Window
You now move j (right pointer) and check if each char exists in the map.

If yes → reduce its count in the map.

Then, maintain a variable:

java
Copy
Edit
int count = map.size(); // total unique characters to be matched
Once the frequency of a char becomes zero, you do:

java
Copy
Edit
count--;
🔁 Step 6: When all characters matched (count == 0)
Now try to shrink the window from the left (i) as much as possible while still keeping all required characters in the window.

At this point:

Check if the current window is the smallest so far.

Update min, si, and e.

Also:

Increase map count for s.charAt(i).

If map value goes above 0, that means we’ve lost a needed char → count++.

✅ Step 7: Return the smallest window
After loop ends, return:

java
Copy
Edit
return min == Integer.MAX_VALUE ? "" : s.substring(si, e + 1);


*/