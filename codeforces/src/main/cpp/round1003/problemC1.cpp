#include <bits/stdc++.h>
 
using i64 = long long;
using u64 = unsigned long long;
using u32 = unsigned;
using u128 = unsigned __int128;

using namespace std;

 std::vector<int> readVector(int n) {
    std::vector<int> vec(n);
    for (int i = 0; i < n; ++i) {
        std::cin >> vec[i];
    }
    return vec;
}

bool check_non_dec(vector<int> a) {
    int n = a.size();
    bool res = true;
    for (int i = 0; i < n-1; i++) {
        res = res && a[i] <= a[i+1];
    }
    return res;
}

void solve() {
    int n, m;
    std::cin >> n >> m;
    std::vector<int> a = readVector(n);
    std::vector<int> b = readVector(m);

    bool res = true;

    a[0] = std::min(a[0], b[0] - a[0]);
    for (int i = 1; i < n; i++) {
        vector<int> candid({a[i], b[0] - a[i]});
        std::sort(candid.begin(), candid.end());
        auto lbi = std::lower_bound(candid.begin(), candid.end(), a[i-1]);
        if (lbi == candid.end()) {
            res = false;
            break;
        }
        a[i] = *lbi;
    }
    
    std::cout << (res ? "yes" : "no") << "\n";
}
 

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    
    int t;
    std::cin >> t;
    
    while (t--) {
        solve();
    }
    
    return 0;
}

