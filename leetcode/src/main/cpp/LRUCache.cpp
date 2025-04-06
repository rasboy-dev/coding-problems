#include <bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode *prev;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
};

class LRUCache {
private:
    ListNode *head;
    ListNode *tail;
    unordered_map<int, ListNode*> cache;
    int size;
    int capacity;
public:
    LRUCache(int capacity) {
        this->capacity = capacity;
        this->size = 0;
        this->head = new ListNode(-1);
        this->tail = head;
        this->cache = unordered_map<int, ListNode*>();
    }
    
    int get(int key) {
        if (cache.find(key) == cache.end()) {
            return -1;
        }
        ListNode *node = cache[key];
        node->prev->next = node->next;
        if (node->next) {
            node->next->prev = node->prev;
        } else {
            tail = node->prev;
        }
        node->next = head->next;
        head->next->prev = node;
        head->next = node;
        node->prev = head;
    }
    
    void put(int key, int value) {
        ListNode *node = new ListNode(value);
        node->next = head->next;
        if (head->next) {
            head->next->prev = node;
        }
        head->next = node;
        node->prev = head;
        if (size == capacity) {
            tail = tail->prev;
        } else {
            size++;
        }
    }
};