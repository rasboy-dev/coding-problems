#include <stdlib.h>
#include <vector>

using namespace std;

class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int tail = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums[i] != nums[tail-1]) {
                nums[tail] = nums[i];
                tail++;
            }
        }
        return tail;
    }
};