#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> tfreq;
        unordered_map<char, int> freq;
        unordered_map<char, int> count;
        for (char c : t) {
            tfreq[c] += 1;
            freq[c] += 1;
        }
        int left = 0, right = 0;
        while (right < s.size() && !freq.empty()) {
            char c = s[right];
            count[c] += 1;
            if (freq.find(c) != freq.end()) {
                freq[c] -= 1;
                if (freq[c] == 0) {
                    freq.erase(c);
                }
            }
            right++;
        }

        if (!freq.empty()) {
            return "";
        } else if (right == s.size()) {
            while (freq.empty()) {
                char c = s[left];
                count[c] -= 1;
                if (tfreq.find(c) != tfreq.end() && count[c] < tfreq[c]) {
                    freq[c] = 1;
                }
                left++;
            }
            return s.substr(left-1, right - left+1);
        }

        int minLeft = left, minRight = right;

        while (right < s.size()) {
            while (right < s.size() && !freq.empty()) {
                char c = s[right];
                count[c] += 1;
                if (freq.find(c) != freq.end()) {
                    freq[c] -= 1;
                    if (freq[c] == 0) {
                        freq.erase(c);
                    }
                }
                right++;
            }
            while (freq.empty()) {
                char c = s[left];
                count[c] -= 1;
                if (tfreq.find(c) != tfreq.end() && count[c] < tfreq[c]) {
                    if (right - left < minRight - minLeft) {
                        minLeft = left, minRight = right;
                    }
                    freq[c] = 1;
                }
                left++;
            }
        }
        return s.substr(minLeft, minRight - minLeft);
    }
};

// int main(int argc, char const *argv[])
// {
//     Solution s;
//     s.minWindow("ab", "b");
//     return 0;
// }
