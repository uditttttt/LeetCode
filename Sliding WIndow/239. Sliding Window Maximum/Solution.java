// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         int i =0;
//         int j = 0;

//         int n = nums.length;

//         int[] ans = new int[n-k+1];
//         int a = 0;
//         ArrayList<Integer> list = new ArrayList<>();

//         while(j<n){

//             while(!list.isEmpty() && list.get(list.size()-1) < nums[j]){
//                 list.remove(list.size()-1);
//             }
//             list.add(nums[j]);
//             if(j-i+1 == k){
//                 ans[a++] = list.get(0);

//                 if(nums[i] == list.get(0)){
//                     list.remove(0);
//                 }
//                 i++;
//              }
//              j++;
//         }

//         return ans;
//     }
// }

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[n-k+1];
        int a = 0;
        int i=0;
        int j=0;

        while(j<n){
            while(!dq.isEmpty() && dq.peekLast() < nums[j]){
                dq.removeLast();
            }

            dq.add(nums[j]);

            if(j-i+1 == k){
                ans[a++] = dq.peekFirst();

                if(nums[i] == dq.peekFirst()){
                    dq.removeFirst();
                }

                i++;
            }
            j++;
        }
        return ans;
    }
}

/*

🧠 How to Identify When to Use Monotonic Deque + Sliding Window?
✅ 1. Sliding Window is Needed If:
The problem involves subarrays of fixed size k

You're asked to process the array one window at a time

You're asked for something like:

Maximum / Minimum of each window

Sum, average, count, etc., within a window

✅ 2. Monotonic Deque is Needed If:
You need the maximum or minimum element in the window in O(1) time

You're doing it for every window (not just one)

A normal approach like sorting or scanning each window is too slow (O(N * k))

🔍 Example Problem:
"Given an array of integers and a number k, return the maximum element in every subarray of size k."

✅ Subarray of size k → ➡️ Use Sliding Window

✅ Maximum of each subarray → ➡️ Need Fast Access → Monotonic Deque




*/