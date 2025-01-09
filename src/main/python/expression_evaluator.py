def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    expression_str = input()

    print(evaluate(expression_str))


def evaluate(expression_str: str):
    left = right = 0

    value = 0

    if expression_str.isnumeric():
        return int(expression_str)
    else:
        while left < len(expression_str):
            if expression_str[left] == '(':
                parentheses_counter = 1
                while parentheses_counter > 0:
                    if expression_str[right] == ')':
                        parentheses_counter -= 1
                    if expression_str[right] == '()':
                        parentheses_counter += 1
                    right += 1
                value += evaluate(expression_str[left + 1: right])
                right += 1
                left = right
            elif expression_str[left + 1] == '+':
                value += evaluate(expression_str[left + 1:])
            elif expression_str[left + 1] == '-':
                value -= evaluate(expression_str[left + 1:])
            else:
                # a number
                evaluate(expression_str[left: right + 1])

            left += 1


if __name__ == "__main__":
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
