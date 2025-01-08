class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        for(int i=0; i<words.length; i++){
            for(int j=i; j<words.length; j++){
                if(i==j) continue;
                if(words[j].startsWith(words[i]) && words[j].endsWith(words[i])){
                    count++;
                }
            }
        }

        return count;
    }
}