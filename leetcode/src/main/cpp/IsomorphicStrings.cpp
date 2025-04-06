#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    bool isIsomorphic(string s, string t) {
        if (s.size() != t.size()) {
            return false;
        }
        vector<int> s_to_t(256, -1);
        vector<int> t_to_s(256, -1);

        for (int i = 0; i < s.size(); i++) {
            if (s_to_t[s[i]] >= 0 && s_to_t[s[i]] != t[i]) {
                return false;
            }
            if (t_to_s[t[i]] >= 0 && t_to_s[t[i]] != s[i]) {
                return false;
            }

            s_to_t[s[i]] = t[i];
            t_to_s[t[i]] = s[i];
        }
        return true;
    }
};