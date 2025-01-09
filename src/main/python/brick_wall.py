import sys


def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    n = int(input())
    brick_rows = []
    for row in sys.stdin.readlines():
        brick_row = [int(brick) for brick in row.split()]
        brick_rows.append(brick_row)

    length = sum(brick_rows[0])
    cuts = [0] * (length - 1)
    for i in range(0, n):
        cur = 0
        for brickLength in brick_rows[i]:
            for _ in range(0, brickLength - 1):
                cuts[cur] += 1
                cur += 1
            cur += 1

    print(cuts)
    print(min(enumerate(cuts), key=lambda x: x[1]))


if __name__ == "__main__":
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
