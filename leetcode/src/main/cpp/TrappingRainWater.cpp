#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int trap(vector<int> height) {
        int n = height.size();
        int l = 0, r = n-1;
        int leftH = height[l], rightH = height[r];
        int v = 0;
        while (l < r) {
            if (leftH < rightH) {
                l++;
                if (height[l] > leftH) {
                    leftH = height[l];
                } else {
                    v += leftH - height[l];
                }
            } else {
                r--;
                if (height[r] > rightH) {
                    rightH = height[r];
                } else {
                    v += rightH - height[r];
                }
            }
        }
        return v;
    }
};