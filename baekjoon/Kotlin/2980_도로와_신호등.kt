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
Data Structures: Triple
Algorithms: Simulation
Statements: for loop, if-else

Result
Time Complexity: O(N)

More
- Key insights: Using modulo operation to determine the light's state; Calculating wait time only for red light.
"""

import java.io.BufferedReader
import java.io.InputStreamReader


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (N, L) = br.readLine().split(" ").map{ it.toInt() }
    val trafficLights = mutableListOf<Triple<Int,Int,Int>>()

    repeat(N) {
        val (D, R, G) = br.readLine().split(" ").map { it.toInt() }
        trafficLights.add(Triple(D, R, G))
    }

    val result = calculateTimeToCross(N, L, trafficLights)
    println(result)
}

fun calculateTimeToCross(N: Int, L:Int, trafficLights:List<Triple<Int,Int,Int>>): Int {
    var currTime = 0
    var currPos = 0

    for (light in trafficLights) {
        val (D, R, G) = light

        // 신호등까지 이동
        currTime += D - currPos
        currPos = D

        val cycleTime = currTime % (R + G)

        if (cycleTime < R) {
            val waitTime = R - cycleTime
            currTime += waitTime
        }
    }
    currTime += L - currPos
    return currTime
}
