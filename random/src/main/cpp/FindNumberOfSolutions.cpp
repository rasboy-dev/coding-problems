#include <bits/stdc++.h>

using namespace std;

bool equation(vector<bool> vars) {
    bool res = true;
    for (int i = 1; i < vars.size() - 2; i++) {
        res = res && (!vars[i] || vars[i+1] || vars[i+2]);
    }
    return res;
}

int countSolutions(vector<bool> vars, int i, bool iVal) {
    vars[i] = iVal;
    if (i == vars.size()-1) {
        return equation(vars) ? 1 : 0;
    }
    
    return countSolutions(vars, i+1, false) + countSolutions(vars, i+1, true);
}

int countSolutions(int n) {
    std::vector<bool> vars(n+1);
    return countSolutions(vars, 1, false) + countSolutions(vars, 1, true);
}

int main() {
    int n = 6;
    std::cout << countSolutions(n) << std::endl; 

    return 0;
}