class Solution {
    public int[] findThePrefixCommonArray(int[] a, int[] b) {
        // 'stat' will be used to track the frequency of elements from 1 to n in both arrays.
        // We initialize an array of size (a.length + 1) because element values range from 1 to n.
        int[] stat = new int[a.length + 1];  

        // 'c' is the array that will hold the result, i.e., the prefix common counts.
        int[] c = new int[a.length];         

        // 'cur' will hold the count of numbers that appear in both A and B up to the current index.
        int cur = 0;                         

        // Iterate over each index of the arrays A and B.
        for (int i = 0; i < a.length; i++) {
            
            // Update the frequency for element a[i] in 'stat'
            stat[a[i]]++;  
            // If 'stat[a[i]]' becomes 2, it means element 'a[i]' has been seen in both arrays
            // (i.e., once in A and once in B), so we increment 'cur'.
            if (stat[a[i]] == 2) {
                cur++;
            }

            // Update the frequency for element b[i] in 'stat'
            stat[b[i]]++;  
            // Similarly, if 'stat[b[i]]' becomes 2, we increment 'cur'.
            if (stat[b[i]] == 2) {
                cur++;
            }

            // After processing both a[i] and b[i], store the current count of common elements
            // in the result array 'c' at index 'i'.
            c[i] = cur;
        }

        // Finally, return the result array 'c', which contains the prefix common counts.
        return c;
    }
}


/*
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int[] ans = new int[B.length];
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            temp.add(B[i]);
            int count = 0;
            for (int j = 0; j <=i; j++) {
                if(temp.contains(A[j])) count++;
              
            }
            ans[i] = count;
        }

        return ans;
    }
}
*/