#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        stack<int> op_stack;

        unordered_map<string, function<int(int, int)>> operations = {
            {"+", [](int a, int b) { return a + b; }},
            {"-", [](int a, int b) { return a - b; }},
            {"*", [](int a, int b) { return a * b; }},
            {"/", [](int a, int b) { return a / b; }}
        };

        for (string& t : tokens) {
            if (operations.find(t) != operations.end()) {
                int b = op_stack.top();
                op_stack.pop();
                int a = op_stack.top();
                op_stack.pop();
                int res = operations[t](a, b);
                op_stack.push(res);
            } else {
                op_stack.push(stoi(t));
            }
        }
        return op_stack.top();
    }
};