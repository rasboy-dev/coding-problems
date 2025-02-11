#include <bits/stdc++.h>

using namespace std;

int main() {
    int t;
    cin >> t;
    int q = t;
    while (q > 0) {
        q--;
        int n, m, k;
        cin >> n >> m >> k;
        if (abs(n - m) > k) {
            cout << "-1" << "\n";
            continue;
        }
        string s = "";
        char c = (n > m ? '0':'1');
        for (int i = 0; i < k; i++) {
            s += c;
        }
        if (n > m) {
            n -= k;
        } else {
            m -= k;
        }
        while (n > 0 && m > 0) {
            if (c == '0') {
                m -=1;
                c = '1';
            } else {
                n -= 1;
                c = '0';
            }
            s += c;
        }
        while (n > 0) {
            s += '0';
            n--;
        }
        while (m > 0) {
            s += '1';
            m--;
        }
        cout << s << "\n";
    }
}
