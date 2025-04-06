#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int n = matrix.size(), m = matrix[0].size();
        vector<int> res;

        int li = 0, lj = 0;
        while (li <= n-li-1 && lj <= m-lj-1) {
            for (int j = lj; j < m - li; j++) {
                res.push_back(matrix[li][j]);
            }
            for (int i = li + 1; i < n - li; i++) {
                res.push_back(matrix[i][m-lj-1]);
            }
            if (li < n-li-1 && lj < m-lj-1) {
                for (int j = m - lj - 2; j >= lj; j--) {
                    res.push_back(matrix[n-li-1][j]);
                }
                for (int i = n - li - 2; i > li; i--) {
                    res.push_back(matrix[i][lj]);
                }
            }
            li++;
            lj++;
        }
        return res;
    }
};