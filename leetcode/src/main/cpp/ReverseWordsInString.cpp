#include <bits/stdc++.h>

using namespace std;


class Solution {
public:
    string reverseWords(string s) {
        int n = s.size();
        reverse(s.begin(), s.end());
        int start = 0;
        int finish = 0;
        while (start < n) {
            while (start < n && s[start] == ' ') {
                start++;
            }
            finish = start;
            while (start < n && s[start] != ' ') { 
                start++;
            }

            reverse(s.begin() + finish, s.begin() + start);
        }
        s += ' ';
        int next = 0;
        int i = 0;
        while (i < n) {
            while (i < n && s[i] == ' ') {
                i++;
            }
            if (i < n) {
                while (i < n && s[i] != ' ') {
                    s[next] = s[i];
                    next++;
                    i++;
                }
                s[next] = ' ';
                next++;
            }
        }
        s.resize(next-1);
        return s;
    }
};