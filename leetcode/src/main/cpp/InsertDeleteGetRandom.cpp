#include <bits/stdc++.h>

class RandomizedSet {
private:
    std::unordered_map<int, int> map;
    std::vector<int> array;

public:
    RandomizedSet() {}

    bool insert(int val) {
        if (map.find(val) != map.end()) {
            return false;
        }
        array.push_back(val);
        map[val] = array.size() - 1;
        return true;
    }

    bool remove(int val) {
        auto it = map.find(val);
        if (it == map.end()) {
            return false;
        }
        int lastElement = array.back();
        int idx = it->second;
        array[idx] = lastElement;
        map[lastElement] = idx;
        array.pop_back();
        map.erase(it);
        return true;
    }

    int getRandom() {
        // std::uniform_int_distribution<> dis(0, array.size() - 1);
        return array[rand() % array.size()];
    }
};
