#include <bits/stdc++.h>
using namespace std;
class Solution {
private:
    const map<char, char> parenthesesPairs = {
        {'(', ')'},
        {'[', ']'},
        {'{', '}'}
    };
public:
    bool isValid(string s) {
        stack<char> st;
        for (char& c : s) {
            if (parenthesesPairs.find(c) != parenthesesPairs.end()) {
                st.push(c);
            } else {
                if (!st.empty() && parenthesesPairs.at(st.top()) == c) {
                    st.pop();
                } else {
                    return false;
                }
            }
        }
        return st.empty();
    }
};