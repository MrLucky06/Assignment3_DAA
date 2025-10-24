import time
import json

# Структура данных Union-Find для отслеживания компонентов
class UnionFind:
def __init__(self, n):
self.parent = list(range(n))
self.rank = [0] * n

def find(self, u):
        if self.parent[u] != u:
self.parent[u] = self.find(self.parent[u])
        return self.parent[u]

def union(self, u, v):
root_u = self.find(u)
root_v = self.find(v)

        if root_u != root_v:
        if self.rank[root_u] > self.rank[root_v]:
self.parent[root_v] = root_u
elif self.rank[root_u] < self.rank[root_v]:
self.parent[root_u] = root_v
            else:
self.parent[root_v] = root_u
self.rank[root_u] += 1
        return True
        return False

def kruskal_algorithm(graph):
start_time = time.time()

mst_edges = []
total_cost = 0
operations_count = 0

nodes = graph["nodes"]
edges = graph["edges"]

        # Сортируем рёбра по весу
edges_sorted = sorted(edges, key=lambda x: x["weight"])

    # Создаем Union-Find для отслеживания компонент
uf = UnionFind(len(nodes))

        for edge in edges_sorted:
u = nodes.index(edge["from"])
v = nodes.index(edge["to"])
weight = edge["weight"]

operations_count += 1
        if uf.union(u, v):  # если u и v находятся в разных компонентах
            mst_edges.append(edge)
total_cost += weight

        end_time = time.time()
execution_time_ms = (end_time - start_time) * 1000

        return mst_edges, total_cost, operations_count, execution_time_ms

# Пример графа (input для small)
graph_small = {
        "nodes": ["A", "B", "C", "D", "E"],
        "edges": [
        {"id": "edge_1", "from": "A", "to": "B", "weight": 4},
        {"id": "edge_2", "from": "A", "to": "C", "weight": 3},
        {"id": "edge_3", "from": "B", "to": "C", "weight": 2},
        {"id": "edge_4", "from": "B", "to": "D", "weight": 5},
        {"id": "edge_5", "from": "C", "to": "E", "weight": 7}
        ]
        }

mst_edges, total_cost, operations_count, execution_time_ms = kruskal_algorithm(graph_small)
print("Kruskal's Algorithm:")
print("MST Edges:", mst_edges)
print("Total Cost:", total_cost)
print("Operations Count:", operations_count)
print("Execution Time (ms):", execution_time_ms)
