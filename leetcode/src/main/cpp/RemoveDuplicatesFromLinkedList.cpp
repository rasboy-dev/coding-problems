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
        ListNode* deleteDuplicates(ListNode* head) {
            if (!head) {
                return head;
            }
            ListNode* dummy = new ListNode(INT_MIN);
            dummy->next = head;
            ListNode* cur = dummy;
            ListNode* node = dummy;
            while (cur->next && cur->next->next) {
                if (cur->val != cur->next->val && cur->next->val != cur->next->next->val) {
                    node->next = cur->next;
                    node = node->next;
                }
                cur = cur->next;
            }
            if (cur->val != cur->next->val) {
                node->next = cur->next;
                node = node->next;
            }
            node->next = NULL;
            return dummy->next;
        }
    };