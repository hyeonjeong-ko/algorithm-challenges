"""
A solution to Sangkeun's traffic light problem.

Metadata
Difficulty: Intermediate
Time Taken: 1 hr
Correct Answer Rate: 52.343%

Analysis
Input:
Expected Time Complexity: O(N)

Implementation
Data Structures: List of tuples
Algorithms: Simulation
Statements: for loop, if-else

Result
Time Complexity: O(N)

More
- Key insights: Using modulo operation to determine the light's state; Calculating wait time only for red light.
"""

import sys

input = sys.stdin.readline

N, L = map(int, input().split())
traffic_lights = [tuple(map(int, input().split())) for _ in range(N)]

curr_time = 0
curr_position = 0

for light in traffic_lights:
    D, R, G = light
    # 트럭을 신호등 위치까지 이동
    curr_time += D - curr_position
    curr_position = D

    # 신호등에 도착하면 현재시간 현재신호등에서 사이클 내 시간 계산
    cycle_time = curr_time % (R + G)

    # 현재 신호등이 빨간 불인지 확인
    if cycle_time < R:
        wait_time = R - cycle_time  # 빨간불이면 대기
        curr_time += wait_time

# 마지막 신호등 이후 도로 끝까지 이동
curr_time += L - curr_position

print(curr_time)
