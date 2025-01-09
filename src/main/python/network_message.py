import sys


def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    n = int(input()) + 1
    connections_str = sys.stdin.readlines()
    connections = {}
    for i in range(0, n):
        connections[i] = []
    for connection in connections_str:
        connection = connection.rstrip().split()
        connection = [int(i) for i in connection]
        connections[connection[0]].append(connection[1:3])

    visited = set()
    to_be_visited = set()
    times = []
    for i in range(0, n):
        to_be_visited.add(i)
        times.append(sys.maxsize)
    times[0] = 0
    while len(to_be_visited) > 0:
        node = min(to_be_visited, key=lambda x: times[x])
        for i, time in connections[node]:
            times[i] = min(times[i], times[node] + time)
        visited.add(node)
        to_be_visited.remove(node)

    print(max(times))


if __name__ == "__main__":
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
