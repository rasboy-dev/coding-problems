#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if (s.empty()) {
            return 0;
        }
        vector<int> chars(128);
        int left = 0, right = 0;
        int len = 1;
        chars[s[0]] = 1;
        while (right < s.size()-1) {
            right++;
            chars[s[right]] += 1;
            while (chars[s[right]] != 1) {
                chars[s[left]] -= 1;
                left++;
            }
            len = max(len, right - left + 1);
        }
        return len;
    }
};