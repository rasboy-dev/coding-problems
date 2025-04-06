#include <bits/stdc++.h>
using namespace std;
class MinStack {
    private:
        stack<int> st;
        stack<int> min_st;
    public:
        MinStack(): st(stack<int>()), min_st(stack<int>()) {
        }
        
        void push(int val) {
            st.push(val);
            if (min_st.empty() || val <= min_st.top()) {
                min_st.push(val);
            }
        }
        
        void pop() {
            int val = st.top();
            st.pop();
            if (val == min_st.top()) {
                min_st.pop();
            }
        }
        
        int top() {
            return st.top();
        }
        
        int getMin() {
            return min_st.top();
        }
    };
