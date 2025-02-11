#include <bits/stdc++.h>

class Solution {
public:
    int canJump(std::vector<int>& nums) {
        int jumps = 0;
        int left = 0, right = nums[0];

        int n = nums.size();
        while (right < n-1) {
            int maxJump = right;
            for (int i = left + 1; i <= right; i++) {
                maxJump = std::max(maxJump, i + nums[i]);
            }
            left = right + 1;
            right = maxJump;
            jumps++;
        }
        return jumps;
    }
};