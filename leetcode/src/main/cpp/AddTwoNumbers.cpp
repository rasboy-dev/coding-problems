#include <bits/stdc++.h>
using namespace std;
class Solution {
private:
    struct ListNode {
        int val;
        ListNode *next;
        ListNode(int x) : val(x), next(NULL) {}
    };
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *res = new ListNode(0);
        ListNode *last = res;
        int carry = 0;
        while (l1 && l2) {
            int digit = l1->val + l2->val + carry;
            carry = digit / 10;
            digit = digit % 10;
            last->next = new ListNode(digit);
            last = last->next;
            l1 = l1->next;
            l2 = l2->next;
        }
        while (l1) {
            int digit = l1->val + carry;
            carry = digit / 10;
            digit = digit % 10;
            last->next = new ListNode(digit);
            last = last->next;
            l1 = l1->next;
        }
        while (l2) {
            int digit = l2->val + carry;
            carry = digit / 10;
            digit = digit % 10;
            last->next = new ListNode(digit);
            last = last->next;
            l2 = l2->next;
        }
        if (carry) {
            last->next = new ListNode(carry);
        }
        return res->next;
    }
};