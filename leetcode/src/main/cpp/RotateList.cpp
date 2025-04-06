#include <bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        ListNode *dummy = new ListNode(0);
        dummy->next = head;
        ListNode *node = head;
        ListNode *last = dummy;

        int n = 0;
        while (node) {
            last = node;
            node = node->next;
            n++;
        }

        if (k == 0 || k % n == 0) {
            return head;
        }
        cout << n % k << "\n";
        last->next = head;
        node = dummy;
        for (int i = 0; i < n-n%k; i++) {
            node = node->next;
        }
        head = node->next;
        node->next = nullptr;
        return head;
    }
};