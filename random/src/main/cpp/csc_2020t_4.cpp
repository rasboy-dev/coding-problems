#include <bits/stdc++.h>

using namespace std;

vector<int> digs({0, 1, 2, 3, 3, 4, 5});

int countNumbers(set<int>& digset, set<int>& uniq, string& n, bool non_zero) {
    if (n.size() == 6) {
        int num = stoi(n);
        if (num % 15 == 0) {
            uniq.insert(num);
        }
    }
    int c = 0;
    for (int d : digs) {
        if (non_zero && d == 0) {
            continue;
        }
        if (digset.find(d) != digset.end()) {
            digset.erase(d);
            n += to_string(d);
            countNumbers(digset, uniq, n, false);
            n.pop_back();
            digset.insert(d);
        }
    }
    return c;
}

int main() {
    std::set<int> set(digs.begin(), digs.end());
    std::set<int> uniq;
    string n = "";
    int c = countNumbers(set, uniq, n, true);
    cout << uniq.size() << endl;
}