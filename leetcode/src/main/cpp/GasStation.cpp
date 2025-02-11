#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int tank = 0, minTank = INT_MAX;
        int n = gas.size();
        for (int i = 0; i< n; i++) {
            tank += gas[i] - cost[i];
            minTank = min(tank, minTank);            
        }
        
        if (tank < 0) {
            return -1;
        }

        int start = n;
        while (minTank < 0) {
            start = (start - 1) % n;
            minTank += gas[start] - cost[start];
        }
        return start;
    }
};