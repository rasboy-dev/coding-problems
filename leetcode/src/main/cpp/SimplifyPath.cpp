#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    string simplifyPath(string path) {
        stack<string> stack;
        int n = path.size();
        int i = 0;
        int size = 0;
        while (i<n) {
            while (i<n && path.at(i) == '/') {
                i++;
            }
            string dir = "";
            while (i < n && path.at(i) != '/') {
                dir += path.at(i);
                i++;
            }
            if (dir == "..") {
                if (!stack.empty()) {
                    size -= stack.top().size();
                    stack.pop();
                }
            } else if (!dir.empty() && dir != ".") {
                stack.push(dir);
                size += dir.size();
            }
        }

        if (stack.empty()) {
            return "/";
        }

        vector<string> simplified(stack.size());
        int j = stack.size()-1;
        while (!stack.empty()) {
            string dir = stack.top();
            stack.pop();
            simplified[j] = dir;
            j--;
        }
        string simplifiedPath = "";
        for (int i = 0; i < simplified.size(); i++) {
            simplifiedPath += "/" + simplified[i];
        }
        return simplifiedPath;
    }
};