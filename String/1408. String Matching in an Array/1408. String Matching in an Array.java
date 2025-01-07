class Solution {
    public List<String> stringMatching(String[] words) {
        // Combine all words into a single string, separated by spaces.
        // Example: words = ["blue", "green", "bluegreen"] -> str = "blue green bluegreen"
        String str = String.join(" ", words);
        
        // Create a list to store the result (words that are substrings of other words)
        List<String> res = new ArrayList<>();
        
        // Iterate through each word in the words array
        for (int i = 0; i < words.length; i++) {
            // Check if the current word appears more than once in the combined string
            // indexOf finds the first occurrence, lastIndexOf finds the last occurrence
            // If they are not equal, it means the word is a substring of another word
            if (str.indexOf(words[i]) != str.lastIndexOf(words[i])) {
                // Add the word to the result list
                res.add(words[i]);
            }
        }
        
        // Return the list of substring words
        return res;
    }
}

/*
class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        for(int i=0; i<words.length; i++){
            for(int j=0; j<words.length; j++){
                if(i==j) continue;
                if(words[j].contains(words[i])){
                    ans.add(words[i]);
                    break;
                }
            }
        }

        return ans;
    }
}
*/