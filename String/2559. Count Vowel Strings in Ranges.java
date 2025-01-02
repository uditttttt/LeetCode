class Solution {
    public int isVowel(String s){
        HashSet<Character> set = new HashSet<>(Arrays.asList('a' ,'e' , 'i' , 'o' , 'u'));
        char l = s.charAt(0);
        char e = s.charAt(s.length()-1);
        if(set.contains(l) && set.contains(e)) return 1;

        return 0;
    }
    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] prefix = new int[words.length];
        prefix[0] = isVowel(words[0]);
        for(int i=1; i<words.length; i++){
            prefix[i] = prefix[i-1] + isVowel(words[i]);
            
        }

        int m = queries.length;
        int ans[]= new int[m];

        for(int i=0; i<m; i++){
            int l = queries[i][0];
            int e = queries[i][1];
            ans[i] = (l != 0) ? prefix[e] - prefix[l-1] : prefix[e]; 
        }

        return ans;
    
    }
}


import java.util.*;

class Solution {
    public int isVowel(String s) {
        // Define a set of vowels
        HashSet<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        char l = s.charAt(0);
        char e = s.charAt(s.length() - 1);
        // Check if both the first and last characters are vowels
        if (set.contains(l) && set.contains(e)) return 1;
        return 0;
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        // Create a prefix sum array
        int[] prfSum = new int[words.length];
        prfSum[0] = isVowel(words[0]);

        for (int i = 1; i < words.length; i++) {
            prfSum[i] = prfSum[i - 1] + isVowel(words[i]);
        }

        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int l = queries[i][0];
            int e = queries[i][1];
            // Use prfSum array for range sum queries
            ans[i] = (l != 0) ? prfSum[e] - prfSum[l - 1] : prfSum[e];
        }

        return ans;
    }
}
