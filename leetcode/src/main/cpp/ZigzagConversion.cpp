#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1 || s.size() <= numRows) {
            return s;
        }
        string res = "";
        for (int row = 0; row < numRows; row++) {
            int i = row;
            res += s[i];
            while (i < s.size()) {
                if (numRows-1 - row > 0) {
                    i += 2*(numRows-1 - row);
                    if (i < s.size()) {
                        res += s[i];
                    }
                }
                if (row > 0) {
                    i += 2 * row;
                    if (i < s.size()) {
                        res += s[i];
                    }
                }
            }
        }
        return res;
    }
};