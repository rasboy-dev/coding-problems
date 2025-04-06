#include <bits/stdc++.h>
using namespace std;

class LRUCache {
private:
    struct ListNode {
        int val;
        int key;
        ListNode *next;
        ListNode *prev;
        ListNode() : key(0), val(0), next(nullptr), prev(nullptr) {}
        ListNode(int key, int x) : key(key), val(x), next(nullptr), prev(nullptr) {}
    };

    ListNode *head;
    ListNode *tail;
    unordered_map<int, ListNode*> cache;
    int size;
    int capacity;
public:
    LRUCache(int capacity) {
        this->capacity = capacity;
        this->size = 0;
        this->head = new ListNode();
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
        return node->val;
    }
    
    void put(int key, int value) {
        ListNode *node = new ListNode(key, value);
        if (cache.find(key) != cache.end()) {
            ListNode *prev = cache[key];
            cache.erase(key);
            if (prev->next == nullptr) {
                tail = tail->prev;
                tail->next = nullptr;
            }
        } else {
            if (size == capacity) {
                cache.erase(tail->key);
                tail = tail->prev;
                tail->next = nullptr;
            } else {
                size++;
            }
        }
        node->next = head->next;
        if (head->next) {
            head->next->prev = node;
        } else {
            tail = node;
        }
        head->next = node;
        node->prev = head;
        cache[key] = node;
    }
};