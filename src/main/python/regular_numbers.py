import heapq

n = 5
i = 1
queue = [1]
while i < n:
    ithRegNum = heapq.heappop(queue)
    print(ithRegNum)
    [heapq.heappush(queue, ithRegNum * div) for div in [2, 3, 5]]
    i += 1
print(heapq.heappop(queue))
