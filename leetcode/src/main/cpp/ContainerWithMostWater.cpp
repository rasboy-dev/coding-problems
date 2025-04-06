#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maxArea(vector<int>& height) {
        int maxLeft = 0, maxRight = height.size()-1;
        int left = 0, right = height.size()-1;
        int maxArea = 0;
        while (left < right) {
            int area = (right - left) * min(height[left], height[right]);
            if (maxArea < area) {
                maxArea = area;
                maxLeft = left;
                maxRight = right;
            }
            if (maxRight > maxLeft) {
                while (left < right && right <= maxRight) {
                    right--;
                }
            } else {
                while (left < right && left <= maxLeft) {
                    left++;
                }
            }
        }
        return maxArea;
    }
};