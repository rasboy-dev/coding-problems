#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        int n = 9;
        unordered_set<char> vCheckSet;
        unordered_set<char> hCheckSet;
        unordered_map<int, unordered_set<char>> sbCheckSets;

        for (int i = 0; i < n; i++) {
            hCheckSet.clear();
            vCheckSet.clear();
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    if (hCheckSet.find(board[i][j]) != hCheckSet.end()) {
                        return false;
                    }
                    hCheckSet.insert(board[i][j]);

                    int subBox = i / 3 * 3 + j / 3;
                    if (sbCheckSets[subBox].find(board[i][j]) != sbCheckSets[subBox].end()) {
                        return false;
                    }
                    sbCheckSets[subBox].insert(board[i][j]);
                }

                if (board[j][i] != '.') {
                    if (vCheckSet.find(board[j][i]) != vCheckSet.end()) {
                        return false;
                    }
                    vCheckSet.insert(board[j][i]);
                }
            }
        }
        return true;
    }
};