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
        ListNode* reverseBetween(ListNode* head, int left, int right) {
            if (left == right) {
                return head;
            }
            ListNode* before = NULL;
            ListNode* from = head;
            int i = 1;
            while (i < left) {
                before = from;
                from = from->next;
                i++;
            }
            ListNode* cur = from;
            ListNode* prev = NULL;
            while (i <= right) {
                ListNode* next = cur->next;
                cur->next = prev;
                prev = cur;
                cur = next->next;
                i++;
            }
            ListNode* to = prev;
            ListNode* after = cur;
            from->next = cur;
            before->next = prev;
            return head;
        }
    };