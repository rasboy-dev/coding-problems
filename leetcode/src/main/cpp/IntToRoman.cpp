#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    std::string intToRoman(int num) {
        std::vector<char> roman = {'I','V','X','L','C','D','M'};
        int one = 0;
        
        std::map<int, std::function<vector<char>(int)>> map = {
            {'0', [&roman](int one)->vector<char> { return {}; }},
            {'1', [&roman](int one)->vector<char> { return {roman[one]}; }},
            {'2', [&roman](int one)->vector<char> { return {roman[one], roman[one]}; }},
            {'3', [&roman](int one)->vector<char> { return {roman[one], roman[one], roman[one]}; }},
            {'4', [&roman](int one)->vector<char> { return {roman[one], roman[one+1]}; }},
            {'5', [&roman](int one)->vector<char> { return {roman[one+1]}; }},
            {'6', [&roman](int one)->vector<char> { return {roman[one+1], roman[one]}; }},
            {'7', [&roman](int one)->vector<char> { return {roman[one+1], roman[one], roman[one]}; }},
            {'8', [&roman](int one)->vector<char> { return {roman[one+1], roman[one],  roman[one], roman[one]}; }},
            {'9', [&roman](int one)->vector<char> { return {roman[one], roman[one+2]}; }}
        };

        std::string nums = std::to_string(num);
        std::deque<char> queue;
        for (int i = nums.size()-1; i >= 0; --i) {
            vector<char> rom = map.at(nums[i])(one);
            for (int j = rom.size()-1; j >= 0; j--) {
                queue.push_front(rom[j]);
            }
            one+=2;
        }
        std::string res(queue.begin(), queue.end());
        return res;
    }
};

int main() {
    Solution s = Solution();
    std::cout << s.intToRoman(55) << std::endl;
}