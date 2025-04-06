#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int lvl = 0;
        while (lvl <= n/2) {
            for (int i = lvl; i < n - lvl - 1; i++) {
                int tmp = matrix[lvl][i];
                matrix[lvl][i] = matrix[n-i-1][lvl];
                matrix[n-i-1][lvl] = matrix[n-lvl-1][n-i-1];
                matrix[n-lvl-1][n-i-1] = matrix[i][n-lvl-1];
                matrix[i][n-lvl-1] = tmp;
            }
            lvl++;
        }
    }  
};