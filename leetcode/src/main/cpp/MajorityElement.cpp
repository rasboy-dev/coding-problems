#include <vector>

using namespace std;

class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int count = 1;
        int mj = nums[0];
        for (auto& num : nums) {
            if (count == 0) {
                mj = num;
                count = 1;
            } else if (mj == num) {
                count++;
            } else {
                count--;
            }            
        }
        return mj;
    }
};