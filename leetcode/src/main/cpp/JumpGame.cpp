#include <bits/stdc++.h>

class Solution {
public:
    bool canJump(std::vector<int>& nums) {
        int maxJump = nums[0];
        int i = 0, n = nums.size();
        while (maxJump < n-1 && i <= maxJump) {
            maxJump = std::max(maxJump, i + nums[i]);
            i++;
        }
        return maxJump >= n-1;
    }
};