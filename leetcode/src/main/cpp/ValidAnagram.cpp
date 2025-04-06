#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.size() != t.size()) {
            return false;
        }
        int n = s.size();
        vector<int> freq(26, 0);
        for (int i = 0; i < n; i++) {
            freq[s[i] - 'a'] += 1;
            freq[t[i] - 'a'] -= 1;
        }
        return all_of(freq.begin(), freq.end(), [](int x) { return x == 0; });
    }  
};