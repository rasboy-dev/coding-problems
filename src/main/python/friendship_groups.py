def codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main():
    n = int(input())
    friendship_list = {}
    for i in range(0, n):
        friendship_list[i] = []
        for j in [int(f) for f in input().split()]:
            friendship_list[i].append(j)

    determined = set()
    friend_groups = []
    for i in range(0, n):
        if i not in determined:
            friend_group = set()
            dfs(i, friendship_list, friend_group)
            for f in friend_group:
                determined.add(f)
            friend_groups.append(friend_group)

    print(friend_groups)


def dfs(i, friendship_list, friend_group):
    friend_group.add(i)
    for f in friendship_list[i]:
        if f not in friend_group:
            friend_group.add(f)
            dfs(f, friendship_list, friend_group)


if __name__ == "__main__":
    codeforces.round995.codeforces.round995.codeforces.round995.codeforces.round995.main()
