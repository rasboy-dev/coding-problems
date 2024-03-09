def main():
    initial_conditions = input()

    has_right_pressure = False
    dominos_in_processing = 0
    result = ''
    for i in range(0, len(initial_conditions)):
        if initial_conditions[i] == 'L':
            dominos_in_processing += 1
            result += process(dominos_in_processing, has_right_pressure, True)
            dominos_in_processing = 0
            has_right_pressure = False
        elif initial_conditions[i] == 'R':
            result += process(dominos_in_processing, has_right_pressure, False)
            dominos_in_processing = 1
            has_right_pressure = True
        else:
            dominos_in_processing += 1

    result += process(dominos_in_processing, has_right_pressure, False)
    print(result)


def process(number_of_dominos, has_right_pressure, has_left_pressure):
    if has_right_pressure and has_left_pressure:
        half = number_of_dominos // 2
        return 'R' * half + ('.' if number_of_dominos % 2 != 0 else '') + 'L' * half
    elif has_right_pressure:
        return 'R' * number_of_dominos
    elif has_left_pressure:
        return 'L' * number_of_dominos
    else:
        return '.' * number_of_dominos


if __name__ == '__main__':
    main()
