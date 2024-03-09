def main():
    string = 'jiujitsu'
    start = end = 0
    uniq_chars = set(string)

    smallest_window = len(string)

    counts = {}
    for end, char in enumerate(string):
        if string[end] not in counts:
            counts[char] = 0
        counts[char] += 1
        while counts[char] > 1:
            counts[char] -= 1
            start += 1

        if len(counts) == len(uniq_chars):
            smallest_window = min(smallest_window, end - start + 1)

        end += 1

    print(smallest_window)


if __name__ == '__main__':
    main()
