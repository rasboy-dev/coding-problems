def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    arr = [-1, 0, 1]

    lo = 0
    hi = len(arr) - 1

    while lo <= hi:
        mid = (lo + hi) // 2
        if arr[mid] > mid:
            hi = mid - 1
        elif arr[mid] < mid:
            lo = mid + 1
        else:
            print(mid)
            return

    print("None")


if __name__ == "__main__":
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
