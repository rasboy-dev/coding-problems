#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
        int n = board.size(), m = board[0].size();

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                int val, neighbors;
                if (board[i][j] > 0) {
                    val = 1;
                    neighbors = board[i][j] - 1;
                } else {
                    val = 0;
                    neighbors = abs(board[i][j]);
                }
                
                if (j < m-1) {
                    neighbors += stateOf(board, i, j+1);
                    addNeighbor(board, val, i, j+1);
                }
                if (i < n-1) {
                    if (j > 0) {
                        neighbors += stateOf(board, i+1, j-1);
                        addNeighbor(board, val, i+1, j-1);
                    }
                    neighbors += stateOf(board, i+1, j);
                    addNeighbor(board, val, i+1, j);
                    if (j < m-1) {
                        neighbors += stateOf(board, i+1, j+1);
                        addNeighbor(board, val, i+1, j+1);
                    }
                }
                if (val == 1) {
                    if (neighbors < 2 || 3 < neighbors) {
                        board[i][j] = 0;
                    } else {
                        board[i][j] = 1;
                    }
                } else {
                    if (neighbors == 3) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    int stateOf(vector<vector<int>>& board, int i, int j) {
        return board[i][j] > 0 ? 1 : 0;
    }

    void addNeighbor(vector<vector<int>>& board, int val, int i, int j) {
        board[i][j] += (board[i][j] > 0) ? val : -val;
    }
};