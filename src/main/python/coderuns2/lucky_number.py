import io
import os
import sys


def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    optim_input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline
    str_num = optim_input().decode().strip()
    if str_num == '0':
        return '1'
    sys.stdout.write(f'{nextLucky(str_num)}\n')


def nextLucky(strNum):
    n = len(strNum) // 2

    left = strNum[:n]
    right = strNum[n:]

    if n == 1:
        lf, r = int(left), int(right)
        if lf == 9 and r == 9:
            return '11'
        elif lf <= r:
            return str(lf + 1) + str(lf + 1)
        return left + left

    sl = dsum(left)
    sr = dsum(right)

    if sl <= sr:
        new_right = next_for_lower_sum(right, sl)
        if len(new_right) > n:
            new_left = str(increment(left))
            if len(new_left) > n:
                zeros = ''.join(['0' for _ in range(n - 1)])
                return f'{zeros}1{zeros}1'
            zeros = ''.join(['0' for _ in range(n)])
            new_right = next_for_higher_sum(zeros, dsum(new_left), n)
            return add_zeros(new_left, n) + new_right
        return left + new_right
    else:
        return left + next_for_higher_sum(right, sl, n)


def next_for_lower_sum(strNum, zum):
    i = 1
    while zum > dsum(strNum[:i]):
        i += 1
    i -= 1
    zeros = ['0' for _ in range(max(len(strNum) - i, 0))]
    front = f'{str(increment(strNum[:i]) if i > 0 else 1)}{"".join(zeros)}'
    rem = zum - dsum(front)
    end = add_zeros(minimize(rem), len(front))
    res = []
    for i in range(len(front)):
        res.append(str(int(front[i]) + int(end[i])))
    return ''.join(res)


def next_for_higher_sum(strNum, zum, n):
    diff = zum - dsum(strNum)
    i = 1
    while diff > i * 9 - dsum(strNum[-i:]):
        i += 1
    if i == 1:
        return add_zeros(increment(strNum, diff), n)
    suffix_sum = diff + dsum(strNum[-i:])
    return f'{strNum[:-i]}{minimize(suffix_sum)}'


def dsum(strNum):
    return sum([int(d) for d in strNum])


def minimize(rem) -> str:
    k = rem // 9
    m = rem % 9
    nines = ['9' for _ in range(k)]
    return f'{(str(m) if m > 0 else "")}{"".join(nines)}'


def add_zeros(strNum: str, length: int) -> str:
    if length - len(strNum) <= 0:
        return strNum
    zeros = ['0' for _ in range(length - len(strNum))]
    return f'{"".join(zeros)}{strNum}'


def increment(strNum: str, times: int = 1) -> str:
    increase = int(strNum[-1]) + times
    if increase // 10 == 0:
        return f'{strNum[:-1]}{str(increase)}'

    res = [str(increase % 10)]
    i = 2
    while i <= len(strNum) and strNum[-i] == '9':
        res.append('0')
        i += 1

    if i > len(strNum):
        res.append('1')
    else:
        res.append(str(int(strNum[-i]) + 1))
        i += 1
        while i <= len(strNum):
            res.append(strNum[-i])
            i += 1
    res.reverse()
    return ''.join(res)


def codeforces.round995.codeforces.round995.test():
    for max_length in range(1, 4):
        for ln in range(pow(10, max_length)):
            for p in range(len(str(ln)), max_length + 1):
                left = add_zeros(str(ln), max_length)
                for r in range(pow(10, max_length)):
                    right = add_zeros(str(r), max_length)
                    str_num = left + right
                    # print(str_num)
                    if brute_force(str_num) != nextLucky(str_num):
                        print(str_num, brute_force(str_num), '!=', nextLucky(str_num))
                        input()


def brute_force(str_num: str):
    n = len(str_num)
    num = int(str_num) + 1
    str_num = add_zeros(str(num), n)
    h = n // 2
    while dsum(str_num[:h]) != dsum(str_num[h:]):
        num += 1
        str_num = add_zeros(str(num), n)
    return add_zeros(str_num, n) if len(str_num) <= n else ('0' * (n // 2 - 1) + '1') * 2


if __name__ == '__main__':
    codeforces.round995.codeforces.round995.test()
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
