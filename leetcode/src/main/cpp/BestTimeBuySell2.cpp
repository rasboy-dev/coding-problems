#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        size_t n = prices.size();
        if (n < 2) {
            return 0;
        }
        int res = 0;
        for (size_t i = 0; i < n - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }
};
    