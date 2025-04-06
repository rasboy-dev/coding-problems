#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size();
        int m = needle.size();
        map<char, int> prefix;
        for (int i = 0; i<needle.size(); i++) {
            prefix[needle[i]] = i;
        }

        int s = 0;

        while (s <= n - m) {
            int j = m - 1;

            while (j >= 0 && haystack[s + j] == needle[j]) {
                j--;
            }
            if (j < 0) {
                return s;
            }
            if (prefix.find(haystack[s + j]) == prefix.end()) {
                s += j+1;
            } else {
                s += max(1, j-prefix[haystack[s+j]]);
            }
        }
        return -1;
    }
};
