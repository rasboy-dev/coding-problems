import sys

def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    sys.stdin.readline().split()
    stops = [int(x) for x in sys.stdin.readline().split()]
    requests = [int(x) for x in sys.stdin.readline().split()]

    n = len(stops)

    for req in requests:
        if req <= stops[0]:
            print(1)
            continue
        if req >= stops[-1]:
            print(n)
            continue

        left = 0
        right = n - 1

        while right - left > 1:
            mid = (left + right) // 2
            if req > stops[mid]:
                left = mid
            else:
                right = mid

        if abs(req - stops[left]) <= abs(req - stops[right]):
            print(left + 1)
        else:
            print(right + 1)

if __name__ == '__main__':
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()