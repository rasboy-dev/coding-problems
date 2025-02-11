#include <bits/stdc++.h>
 
using i64 = long long;
using u64 = unsigned long long;
using u32 = unsigned;
using u128 = unsigned __int128;
 
void solve() {
    std::string s;
    std::cin >> s;
    
    bool has_pair = false;
    for (int i = 0; i < s.size()-1; i++) {
        has_pair = has_pair || s[i] == s[i+1];
    }
    
    std::cout << (has_pair ? 1 : s.size()) << "\n";
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

std::vector<int> readVector(int n) {
    std::vector<int> vec(n);
    for (int i = 0; i < n; ++i) {
        std::cin >> vec[i];
    }
    return vec;
}