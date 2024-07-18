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
Data Structures: Array
Algorithms: Simulation
Statements: for loop, if-else

Result
Time Complexity: O(N)

More
- Key insights: Using modulo operation to determine the light's state; Calculating wait time only for red light.
"""


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] trafficLights = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            trafficLights[i][0] = Integer.parseInt(st.nextToken());
            trafficLights[i][1] = Integer.parseInt(st.nextToken());
            trafficLights[i][2] = Integer.parseInt(st.nextToken());
        }

        int result = calculateTimeToCross(N, L, trafficLights);
        System.out.println(result);
    }

    public static int calculateTimeToCross(int N, int L, int[][] trafficLights){
        int currTime = 0;
        int currPos = 0;

        for(int i = 0; i < N; i++) {
            int D = trafficLights[i][0];
            int R = trafficLights[i][1];
            int G = trafficLights[i][2];

            //신호등으로 위치 이동
            currTime += D - currPos;
            currPos = D;

            //현재 위치 현재 신호등에서의 cycle time 계산
            int cycleTime = currTime % (R + G);

            if (cycleTime < R) {
                int waitTime = R - cycleTime;
                currTime += waitTime;
            }
        }
        currTime += L - currPos;
        return currTime;
    }

}
