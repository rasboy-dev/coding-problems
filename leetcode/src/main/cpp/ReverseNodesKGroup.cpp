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
    ListNode* reverseKGroup(ListNode* head, int k) {
        if (k < 2) {
            return head;
        }
        ListNode* dummy = new ListNode(0);
        ListNode* before = dummy;
        ListNode* after = head->next;
        ListNode* from = head;
        ListNode* to = head;
        int i = 1;
        while (to) {
            if (i == k) {
                ListNode* prev = NULL;
                ListNode* cur = from;
                while (cur != after) {
                    ListNode* next = cur->next;
                    cur->next = prev;
                    prev = cur;
                    cur = next;
                }
                before->next = to;
                from->next = after;
                before = from;
                from = after;
                to = after;
                if (to) {
                    after = to->next;
                }
                i = 1;
            } else {
                to = to->next;
                if (to) {
                    after = to->next;
                }
                i++;
            }
        }
        return dummy->next;
    }
};