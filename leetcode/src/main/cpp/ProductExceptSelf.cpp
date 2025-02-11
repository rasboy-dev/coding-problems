#include <bits/stdc++.h>

class Solution {
public:
    std::vector<int> productExceptSelf(std::vector<int>& nums) {
        int n = nums.size();
        std::vector<int> res(n);

        res[0] = 1;

        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        int suffix = 1;
        for (int i = n-2; i >= 0; i--) {
            suffix *= nums[i+1];
            res[i] = res[i+1] * suffix;
        }

        return res;
    }
};