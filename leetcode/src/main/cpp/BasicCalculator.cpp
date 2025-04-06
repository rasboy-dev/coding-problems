#include <bits/stdc++.h>
using namespace std;
class Solution {
private:
    const unordered_map<char, function<int(int,int)>> operations = {
        {'+', [](int a, int b) {return a+b;}},
        {'-', [](int a, int b) {return a-b;}},
        {'*', [](int a, int b) {return a*b;}},
        {'/', [](int a, int b) {return a/b;}}
    };

    const unordered_map<char, int> priorities = {
        {'(', 0},
        {')', 0},
        {'+', 1},
        {'-', 2},
        {'*', 3},
        {'/', 4},
    };

    bool is_operation(char t) {
        return operations.find(t) != operations.end();
    }

    void resolve_last(stack<int>& st, stack<char>& op_st) {
        int b = st.top();
        st.pop();
        int a = st.top();
        st.pop();
        char operation = op_st.top();
        op_st.pop();
        st.push(operations.at(operation)(a,b));
        cout << a << operation << b << "=" << st.top() << "\n";
    }

    enum class LastType {
        NUM,
        OP
    };
public:
    int calculate(string s) {
        int n = s.size();
        int i = 0;
        stack<int> st;
        stack<char> op_st;
        LastType last = LastType::OP;
        bool negate_next = false;
        while (i < n) {
            if (s[i] == ' ') {
                i++;
                continue;
            }
            if ('0' <= s[i] && s[i] <= '9') {
                string str_num = "";
                while (i < n && '0' <= s[i] && s[i] <= '9') {
                    str_num += s[i];
                    i++;
                }
                int num = stoi(str_num);
                st.push(num);
                last = LastType::NUM;
            }
            if (s[i] == '(') {
                op_st.push(s[i]);
                last = LastType::OP;
            }
            if (s[i] == ')') {
                while (op_st.top() != '(') {
                    resolve_last(st, op_st);
                }
                op_st.pop();
            }
            if (is_operation(s[i])) {
                if (s[i] == '-' && last == LastType::OP) {
                    st.push(-1);
                    op_st.push('*');
                } else {
                    while (!op_st.empty() && priorities.at(s[i]) <= priorities.at(op_st.top())) {
                        resolve_last(st, op_st);
                    }
                    op_st.push(s[i]);
                }
                last = LastType::OP;
            }
            i++;
        }
        while (!op_st.empty()) {
            resolve_last(st, op_st);
        }
        return st.top();
    }
};