import sys

def main():

    lines = sys.stdin.readlines()
    a, b, n = [int(x) for x in lines]

    if n == 1:
        print('Yes' if a > b else 'No')
        return

    maxA = a
    minB = b // n + (1 if b % n != 0 else 0)

    if maxA > minB:
        print('Yes')
    else:
        print('No')

    return

if __name__ == "__main__":
    main()