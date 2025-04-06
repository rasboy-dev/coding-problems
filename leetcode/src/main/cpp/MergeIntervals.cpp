#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        int n = intervals.size();
        vector<int> begins(n);
        vector<int> ends(n);
        for (int i = 0; i < n; i++) {
            begins[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        sort(begins.begin(), begins.end());
        sort(ends.begin(), ends.end());

        vector<vector<int>> ans;
        int i = 0, j = 0;
        int open_intervals = 0;
        int current_interval_begin = begins[0];
        while (i < n && j < n) {
            if (begins[i] <= ends[j]) {
                if (open_intervals == 0) {
                    current_interval_begin = begins[i];
                }
                open_intervals++;
                i++;
            }
            if (begins[i] > ends[j]) {
                open_intervals--;
                if (open_intervals == 0) {
                    ans.push_back(vector<int>({current_interval_begin, ends[j]}));
                }
                j++;
            }
        }

        return ans;
    }
};