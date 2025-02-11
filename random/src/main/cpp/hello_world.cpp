#include <iostream>
#include <string>
using namespace std;

int main() {
    cout << "Hello, World!" << endl;
    string s;
    cin >> s;
    cout << "String: " << s << endl;
    int t = stoi(s);
    cout << "Converted number: " << t << endl;

    for (int i : {1, 2, 3}) {
        cout << i << endl;
    }

    for (int i = 0; i < t; i++) {
        cout << i << endl;
    }   

    return 0;
}