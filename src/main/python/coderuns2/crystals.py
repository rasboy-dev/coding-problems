import sys


def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    str1 = input()
    str2 = input()
    str3 = input()

    i = j = k = 0
    word = ''
    while i < len(str1) or j < len(str2)  or k < len(str3):
        char1 = str1[i]
        char2 = str2[j]
        char3 = str3[k]

        if char1 != char2 or char1 != char3:
            print('IMPOSSIBLE')
            return

        start_i, start_j, start_k = i, j ,k
        while i < len(str1) - 1 and char1 == str1[i]:
            i += 1
        while j < len(str2) - 1 and char2 == str2[j]:
            j += 1
        while k < len(str3) - 1 and char3 == str3[k]:
            k += 1
        count_i = i - start_i
        count_j = j - start_j
        count_k = k - start_k
        counts = [count_i, count_j, count_k]
        counts.sort()
        for _ in range(counts[1]):
            word += str1[start_i]

    print(word)

if __name__ == '__main__':
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()