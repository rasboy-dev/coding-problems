import sys


def main():
    graph = {}
    n = int(input())
    for con in sys.stdin.readlines():
        con = con.rstrip()
        (i, j) = map(int, con.split())
        graph[i] = graph[i] if i in graph else []
        graph[i].append(j)
        graph[j] = graph[j] if j in graph else []
        graph[j].append(i)

    checked = set()

    hasCycle = False
    for i in range(1, n + 1):
        if i not in checked:
            visited = set()
            hasCycle |= dfs(i, -1, graph, visited)
            [checked.add(z) for z in visited]
            print(f'checked: {checked}')

    print(hasCycle)


def dfs(i, prev, graph, visited) -> bool:
    print(f'i: {i}, visited: {i in visited}')
    if i in visited:
        return True
    visited.add(i)
    for j in graph[i]:
        if j != prev:
            if dfs(j, i, graph, visited):
                return True

    return False


if __name__ == "__main__":
    main()
