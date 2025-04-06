#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> num_set;
        for (int num : nums) {
            num_set.insert(num);
        }
        int max_len = 0;
        for (int num : nums) {
            if (num_set.find(num-1) == num_set.end()) {
                int cur = num;
                while (num_set.find(cur) != num_set.end()) {
                    cur += 1;
                }
                max_len = max(max_len, cur - num);
            }
        }
        return max_len;
    }
};