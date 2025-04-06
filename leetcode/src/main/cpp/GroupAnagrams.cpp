#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> anagram_map;
        for (string str : strs) {
            string repr = str;
            sort(repr.begin(), repr.end());
            anagram_map[repr].push_back(str);
        }
        vector<vector<string>> groups;
        for (auto& [repr, group] : anagram_map) {
            groups.push_back(group);
        }
        return groups;
    }
};