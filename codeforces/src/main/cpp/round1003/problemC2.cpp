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

int find_bi(vector<int> b, int ai, int t) {
    int l = 0, r = b.size() - 1;
    int bi = -1;
    if (b.back() - ai < t) 
        return -1;
    while (l <= r) {
        int mid = l + (r-l) / 2;
        if (b[mid] - ai >= t) {
            bi = mid;
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return bi;
}

void solve() {
    int n, m;
    std::cin >> n >> m;
    std::vector<int> a = readVector(n);
    std::vector<int> b = readVector(m);
    std::sort(b.begin(), b.end());

    bool res = true;

    int max_int = std::numeric_limits<int>::max();
    a[0] = std::min(a[0], b[0] - a[0]);
    for (int i = 1; i < n; i++) {
        int ai = max_int;

        if (a[i] >= a[i-1]) ai = a[i];
        int bi = find_bi(b, a[i], a[i-1]);
        if (bi >= 0) {
            int ai_new = b[bi] - a[i];
            if (ai_new >= a[i-1]) ai = std::min(ai, ai_new);
        }
        if (ai == max_int) {
            res = false;
            break;
        }
        a[i] = ai;
    }

    // std::cout << "a: ";
    // for (int i : a) std::cout << i << " ";
    // std::cout << "\n";
       
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
