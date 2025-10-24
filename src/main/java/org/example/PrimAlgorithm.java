import heapq
import time
import json

def prim_algorithm(graph):
start_time = time.time()

    # Initial setup for the priority queue
mst_edges = []
total_cost = 0
operations_count = 0

nodes = graph["nodes"]
edges = graph["edges"]

        # Create an adjacency list representation of the graph
adj_list = {node: [] for node in nodes}
        for edge in edges:
adj_list[edge["from"]].append((edge["weight"], edge["to"]))
adj_list[edge["to"]].append((edge["weight"], edge["from"]))

        # Start Prim's Algorithm from an arbitrary node (A)
start_node = nodes[0]
visited = {node: False for node in nodes}
min_heap = [(0, start_node, None)]  # (cost, node, from_node)

        while min_heap:
weight, current_node, from_node = heapq.heappop(min_heap)
operations_count += 1

        if visited[current_node]:
        continue

visited[current_node] = True
        if from_node:
        mst_edges.append({"from": from_node, "to": current_node, "weight": weight})
total_cost += weight

        for neighbor_weight, neighbor in adj_list[current_node]:
        if not visited[neighbor]:
        heapq.heappush(min_heap, (neighbor_weight, neighbor, current_node))

end_time = time.time()
execution_time_ms = (end_time - start_time) * 1000

        return mst_edges, total_cost, operations_count, execution_time_ms

# Sample graph input
        graph = {
        "nodes": ["A", "B", "C", "D", "E"],
        "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
        ]
        }

mst_edges, total_cost, operations_count, execution_time_ms = prim_algorithm(graph)
print("Prim's Algorithm:")
print("MST Edges:", mst_edges)
print("Total Cost:", total_cost)
print("Operations Count:", operations_count)
print("Execution Time (ms):", execution_time_ms)
