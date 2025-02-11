#include <bits/stdc++.h>

using namespace std;

int main() {
    int t;
    cin >> t;
    int q = t;
    while (q > 0) {
        int n;
        cin >> n;
        vector<int> clocks(n);
        for (int i =0; i < n; i++) {
            cin >> clocks[i];
        }
        bool possible = true;
        for (int i = 0; i<n;i++) {
            if (clocks[i] <= i * 2 || clocks[i] <= (n - i - 1) * 2) {
                possible = false;
                break;
            }
        }
        cout << (possible ? "yes" : "no") << "\n";

        q--;
    }
}