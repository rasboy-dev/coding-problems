#include <bits/stdc++.h>

class Solution {
public:
    int romanToInt(std::string s) {
        std::vector<int> roman(128);
        roman['I'] = 1;
        roman['V'] = 5;
        roman['X'] = 10;
        roman['L'] = 50;
        roman['C'] = 100;
        roman['D'] = 500;
        roman['M'] = 1000;

        int n = s.size();
        char prev = s.back();
        int num = roman[prev];
        for (int i = n-2; i >= 0; i--) {
            char c = s[i];
            if (roman[c] < roman[prev]) {
                num -= roman[c];
            } else {
                num += roman[c];
            }
            prev = c;
        }
    
        return num;
    }
};