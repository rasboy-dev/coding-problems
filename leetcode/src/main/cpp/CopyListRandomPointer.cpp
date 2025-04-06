#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int val;
    Node* next;
    Node* random;
    
    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};

class Solution {
public:
    Node* copyRandomList(Node* head) {
        unordered_map<int, Node*> new_map;
        Node* tail = head;
        while (tail) {
            new_map[tail->val] = new Node(tail->val);
            tail = tail->next;
        }
        tail = head;
        while (tail) {
            if (tail->random) {
                new_map[tail->val]->random = new_map[tail->random->val];
            }
            if (tail->next) {
                new_map[tail->val]->next = new_map[tail->next->val];
            }
            tail = tail->next;
        }
        return new_map[head->val];
    }
};