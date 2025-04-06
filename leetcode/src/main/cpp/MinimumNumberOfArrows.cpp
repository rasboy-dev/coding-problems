#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        int n = points.size();
        sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) { return a[0] < b[0]; });
        vector<int> intersection = points[0];
        int arrows = 0;
        int i = 1;
        while (i < n) {
            if (points[i][0] <= intersection[1]) {
                intersection[0] = max(points[i][0], intersection[0]);
                intersection[1] = min(points[i][1], intersection[1]);
            } else {
                arrows += 1;
                intersection[0] = points[i][0];
                intersection[1] = points[i][1];
            }
            i++;
        }
        return arrows+1;
    }
};