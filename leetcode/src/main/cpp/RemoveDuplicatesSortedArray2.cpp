#include <stdlib.h>
#include <vector>

using namespace std;

// 1 1 1 2 2 3 4 5 5

class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if (nums.size() <= 2) {
            return nums.size();
        }
        int tail = 2;
        for (int i = 2; i < nums.size(); i++) {
            if (nums[i] != nums[tail-2]) {
                nums[tail] = nums[i];
                tail++;
            }
        }
        return tail;
    }
};