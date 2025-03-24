import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length; // Number of meetings
        int c = 0; // Counter to track the number of free days

        // Step 1: Sort the meetings based on their start day in ascending order
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        // Step 2: Initialize maxR (right boundary of last attended meeting) with the first meeting's end day
        int maxR = meetings[0][1];

        // Step 3: Iterate through meetings to find gaps between them
        for(int i = 1; i < n; i++) {
            if (meetings[i][0] > maxR) { // If a gap exists between previous meeting and current meeting
                c += meetings[i][0] - maxR - 1; // Add the number of free days between meetings
            }

            // Update maxR to track the farthest attended day
            if (maxR < meetings[i][1]) {
                maxR = meetings[i][1];
            }
        }

        // Step 4: Count free days before the first meeting (from day 1 to first meeting start - 1)
        c += meetings[0][0] - 1;

        // Step 5: Count free days after the last meeting (from last meeting end + 1 to total days)
        c += days - maxR;

        return c; // Return the total count of free days
    }
}

/*

Intuition Behind the Code
Sort the meetings by start day to process them in order.

Track the last attended day (maxR) to identify gaps between meetings.

Iterate through the sorted meetings, checking if there are gaps between the end of the last meeting and the start of the current one.

Count free days:

Before the first meeting.

Between meetings.

After the last meeting.

Return the total count of free days.

*/