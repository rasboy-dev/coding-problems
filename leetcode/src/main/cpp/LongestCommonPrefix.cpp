#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        string res = "";
        for (int j = 0; j < strs[0].size(); j++) {
            for (int i = 0; i < strs.size(); i++) {
                if (strs.size() == j || strs[i][j] != strs[0][j]) {
                    return res;
                }
            }
            res += strs[0][j];
        }
        return res;
    }
};