#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    bool wordPattern(string pattern, string s) {
        int start = 0;
        int p = 0;
        unordered_map<string, char> w_to_c;
        unordered_map<char, string> c_to_w;
        for (int i = 0; i<=s.size(); i++) {
            if (s[i] == ' ' || i==s.size()) {
                if (p == pattern.size()) {
                    return false;
                }
      
                string word = s.substr(start, i-start);
                if (w_to_c.find(word) != w_to_c.end() && w_to_c[word] != pattern[p]) {
                    return false;
                }
                if (c_to_w.find(pattern[p]) != c_to_w.end() && c_to_w[pattern[p]] != word) {
                    return false;
                }
                w_to_c[word] = pattern[p];
                c_to_w[pattern[p]] = word;
                p++;
                start = i + 1;
            }
        }
        return p==pattern.size();
    }
};