def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    arr = [1, 2, 4, 5, 7, 9, 0]
    squares = {(i * i) for i in arr}
    for i in range(0, len(arr)):
        for j in range(i + 1, len(arr)):
            a, b = arr[i], arr[j]
            if a * a + b * b in squares:
                print(True)
                return
    print(False)


if __name__ == "__main__":
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
