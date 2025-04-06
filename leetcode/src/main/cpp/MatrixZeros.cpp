#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int n = matrix.size(), m = matrix[0].size();
        
        bool zeroInFirstCol = false;
        bool zeroInFirstRow = false;

        for (int i = 0; i<n; i++) {
            zeroInFirstCol = zeroInFirstCol || matrix[i][0] == 0;
        }
        for (int j = 0; j<n; j++) {
            zeroInFirstRow = zeroInFirstRow || matrix[0][j] == 0;
        }

        for (int i=0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (zeroInFirstCol) {
            for (int i=0; i<n; i++) {
                matrix[i][0] = 0;
            }
        }
        if (zeroInFirstRow) {
            for (int j=0; j<m; j++) {
                matrix[0][j] = 0;
            }
        }
    }
};