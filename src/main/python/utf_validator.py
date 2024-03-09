def validate2(arr):
    bytes_counter = 0
    for i in arr:
        leading_ones = 0
        while i & 128:
            leading_ones += 1
            i = i << 1

        if leading_ones == 0:
            if bytes_counter:
                return False
        elif leading_ones == 1:
            if not bytes_counter:
                return False
            bytes_counter -= 1
        else:
            if bytes_counter:
                return False
            bytes_counter = leading_ones - 1

    return False if bytes_counter else True


def main():
    arr = [int(i) for i in input().split()]
    print(validate2(arr))


if __name__ == "__main__":
    main()
