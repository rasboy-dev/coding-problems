import sys

def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    string = input()

    counters = {}
    max = 0
    maxPair = ''

    for i in range(len(string) - 1):
        if string[i] != ' ' and string[i + 1] != ' ':
            pair = string[i] + string[i + 1]
            counters[pair] = counters.get(pair, 0) + 1
            if max < counters[pair] or max == counters[pair] and pair > maxPair:
                max = counters[pair]
                maxPair = pair
    print(counters)
    print(maxPair)

if __name__ == '__main__':
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()