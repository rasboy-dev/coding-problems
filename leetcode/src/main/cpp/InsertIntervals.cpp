#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        int n = intervals.size();
        int m = newInterval.size();

        if (n == 0) {
            return vector<vector<int>>({newInterval});
        }

        vector<vector<int>> ans;
        int i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) {
            ans.push_back(intervals[i]);
            i++;
        }
        
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = min(intervals[i][0], newInterval[0]);
            newInterval[1] = max(intervals[i][1], newInterval[1]);
            i++;
        }
        ans.push_back(newInterval);

        for (int j = i; j < n; j++) {
            ans.push_back(intervals[j]);
        }
        return ans;
    }
};