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
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode *head = new ListNode(0);
        ListNode *tail = head;
        while (list1 && list2) {
            if (list1->val < list2->val) {
                tail->next = new ListNode(list1->val);
                list1 = list1->next;
            } else {
                tail->next = new ListNode(list2->val);
                list2 = list2->next;
            }
            tail = tail->next;
        }
        while (list1) {
            head->next = new ListNode(list1->val);
            list1 = list1->next;
        }
        while (list2) {
            head->next = new ListNode(list2->val);
            list2 = list2->next;
        }
        return head->next;
    }
};