#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> lines;
        int left = 0, right = 1;
        int length = words[0].size();
        while (left < words.size()) {
            while (right < words.size() && length + 1 + words[right].size() <= maxWidth) {
                length += 1 + words[right].size();
                right++;
            }

            int wordsCount = right - left;
            string line = words[left];

            int extraSpaces = maxWidth - length;

            if (wordsCount > 1) {
                int spaces = extraSpaces / (wordsCount-1);
                int spacesRem = extraSpaces % (wordsCount-1);
                for (int i = left+1; i < right; i++) {
                    line.append(1, ' ');
                    if (right < words.size()) {
                        line.append(spaces, ' ');
                        if (spacesRem > 0) {
                            line.append(1, ' ');
                            spacesRem--;
                        }
                    }
                    line.append(words[i]);
                }
            }

            if (right == words.size() || wordsCount == 1) {
                line.append(extraSpaces, ' ');
            }

            lines.push_back(line);
            left = right;
            length = (right < words.size()) ? words[right].size() : 0;
            right++;
        }

        return lines;
    }
};