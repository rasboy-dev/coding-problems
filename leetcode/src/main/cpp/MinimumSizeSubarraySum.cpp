#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int n = nums.size();
        nums.push_back(0);
        int left = 0, right = 0;
        int sum = nums[0];
        int len = INT_MAX;
        while (right < n) {
            if (sum < target) {
                right++;
                sum += nums[right];
            } else {
                len = min(len, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        if (sum < target && left == 0 && right == n) {
            return 0;
        }
        return len;
    }
};