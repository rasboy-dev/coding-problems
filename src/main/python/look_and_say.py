def main():
    pass

    n = 1
    nth = 5

    term = str(n)
    for i in range(1, nth):
        next_term = []
        cur_char = ''
        cur_char_counter = 0
        for char in term:
            if char == cur_char:
                cur_char_counter += 1
            else:
                if cur_char:
                    next_term.append(str(cur_char_counter) + cur_char)
                cur_char = char
                cur_char_counter = 1
        next_term.append(str(cur_char_counter) + cur_char)
        term = ''.join(next_term)
        print(term)

    print()
    print(term)


if __name__ == "__main__":
    main()
