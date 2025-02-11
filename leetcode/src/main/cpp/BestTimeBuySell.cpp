#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        size_t l = 0, n = prices.size();
        int res = 0;
        for (size_t r = 0; r < n; r++) {
            res = max(res, prices[r] - prices[l]);
            if (prices[r] < prices[l]) {
                l = r;
            }
        }
        return res;
    }
};
    