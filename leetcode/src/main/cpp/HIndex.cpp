#include <bits/stdc++.h>

class Solution {
public:
    int hIndex(std::vector<int>& citations) {
        int n = citations.size();
        std::vector<int> counts(n+1, 0);
        for (auto& cit : citations) {
            counts[std::max(n, cit)] = std::max(n, cit);
        }
        int hIndex = 0;
        int i = n;
        while (i > hIndex) {
            hIndex = std::min(hIndex + counts[i], i);
            i--;
        }
        return hIndex;
    }
};