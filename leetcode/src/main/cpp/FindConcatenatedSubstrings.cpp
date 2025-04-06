#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        int n = words.size();
        int k = words[0].size();
        int len = s.size();

        unordered_map<string, int> wordCounts;
        for (string w : words) {
            wordCounts[w] += 1;
        }
        
        vector<int> ans;
        for (int start = 0; start < k; start++) {
            unordered_map<string, int> freq;
            int left = start, right = start;
            int count = 0;
            while (right + k <= len) {
                string word = s.substr(right, k);
                
                freq[word] += 1;
                count += 1;
                right += k;

                while (freq[word] > wordCounts[word]) {
                    string w = s.substr(left, k);
                    freq[w] -= 1;
                    count -= 1;
                    left += k;
                }

                if (count == n) {
                    ans.push_back(left);
                }
            }
        }
        return ans;
    }  
};