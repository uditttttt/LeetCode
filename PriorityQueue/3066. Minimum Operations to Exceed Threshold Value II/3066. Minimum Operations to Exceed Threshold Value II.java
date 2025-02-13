 // Give TLE

// class Solution {
//     public int minOperations(int[] nums, int k) {
//         ArrayList<Long> temp = new ArrayList<>();
//         for(int n : nums){
//             long a = n;
//             temp.add(a);
//         }
//         int count = 0;
//         Collections.sort(temp);
//         if(temp.get(0) >= k) return count; 
//         while(temp.size() > 1){
//             count++;
//             long a = temp.remove(0);
//             long b = temp.remove(0);
//             long t = Math.min(a,b)*2 + Math.max(a,b);
//             temp.add(t);
//             Collections.sort(temp);
//             if(temp.get(0) >=k) return count;
//         }
//         return count;
//     }
// }

// class Solution {
//     public int minOperations(int[] nums, int k) {
//         PriorityQueue<Long> pq = new PriorityQueue<>();
//         int count = 0;
//         for(int n : nums){
//             long a = n;
//             pq.add(a);
//         }
//         while(pq.size() > 1 && pq.peek() < k ){
//             count++;
//             long x  = pq.remove();
//             long y = pq.remove();
//             long t = Math.min(x , y)*2 + Math.max(x , y);
//             pq.add(t);
            
//         } 

//         return count;
//     }
// }


// More optimised code

class Solution {
  public int minOperations(int[] nums, int k) {
      // Initialize a PriorityQueue (min-heap) to store elements less than k
      PriorityQueue<Integer> pq = new PriorityQueue<>();

      // Iterate through the input array and add elements less than k to the PriorityQueue
      for (int num : nums) {
          if (num < k) {
              pq.add(num); // Add elements that need to be processed
          }
      }

      // Initialize a counter to keep track of the number of operations performed
      int op = 0;

      // Process elements in the PriorityQueue until it is empty
      while (!pq.isEmpty()) {
          // Remove the smallest element from the PriorityQueue
          int x = pq.poll();
          op++; // Increment the operation counter for each element processed

          // If the PriorityQueue is empty after removing x, break out of the loop
          if (pq.isEmpty()) {
              break;
          }

          // Remove the next smallest element from the PriorityQueue
          int y = pq.poll();

          // Combine x and y using the operation: 2 * x + y
          // Use 'long' to avoid integer overflow during calculation
          long up = 2L * x + y;

          // If the new combined value is still less than k, add it back to the PriorityQueue
          if (up < k) {
              pq.add((int) up); // Cast back to int and add to the queue for further processing
          }
      }

      // Return the total number of operations performed
      return op;
  }
}