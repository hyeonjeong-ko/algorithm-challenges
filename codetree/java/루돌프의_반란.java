/*
https://www.codetree.ai/training-field/frequent-problems/problems/rudolph-rebellion/description?page=1&pageSize=20
simulation
*/

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, P, C, D;
    static int[][] v;
    static int[] score;
    static int[] alive;
    static int[] wakeupTurn;
    static int[][] santa;
    static int ri, rj; // 루돌프 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        v = new int[N][N];
        score = new int[P + 1];
        alive = new int[P + 1];
        wakeupTurn = new int[P + 1];
        santa = new int[P + 1][2];

        // 루돌프 위치
        st = new StringTokenizer(br.readLine());
        ri = Integer.parseInt(st.nextToken()) - 1;
        rj = Integer.parseInt(st.nextToken()) - 1;
        v[ri][rj] = -1; // 루돌프 위치 표시

        Arrays.fill(alive, 1); // 모든 산타는 살아있음
        alive[0] = 0;          // 0번째 산타는 없음
        Arrays.fill(wakeupTurn, 1); // 깨어날 턴 정보 초기화

        // 산타 위치 입력
        for (int i = 1; i <= P; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken()) - 1;
            int sj = Integer.parseInt(st.nextToken()) - 1;
            santa[n][0] = si;
            santa[n][1] = sj;
            v[si][sj] = n;
        }

        // 시뮬레이션 진행
        for (int turn = 1; turn <= M; turn++) {
            // [0] 모두 탈락 시 종료
            if (Arrays.stream(alive).sum() == 0) {
                break;
            }

            // [1-1] 루돌프 이동: 가장 가까운 산타 찾기
            int mn = 2 * N * N;
            List<int[]> mlst = new ArrayList<>();
            for (int idx = 1; idx <= P; idx++) {
                if (alive[idx] == 0) continue; // 탈락한 산타는 제외

                int si = santa[idx][0];
                int sj = santa[idx][1];
                int dist = (ri - si) * (ri - si) + (rj - sj) * (rj - sj);

                if (mn > dist) {
                    mn = dist;
                    mlst.clear();
                    mlst.add(new int[]{si, sj, idx});
                } else if (mn == dist) {
                    mlst.add(new int[]{si, sj, idx});
                }
            }

            mlst.sort((a, b) -> {
                if (a[0] == b[0]) return Integer.compare(b[1], a[1]);
                return Integer.compare(b[0], a[0]);
            });

            int[] target = mlst.get(0);
            int si = target[0], sj = target[1], mnIdx = target[2];

            // [1-2] 대상 산타 방향으로 루돌프 이동
            int rdi = 0, rdj = 0;
            if (ri > si) rdi = -1;
            else if (ri < si) rdi = 1;

            if (rj > sj) rdj = -1;
            else if (rj < sj) rdj = 1;

            v[ri][rj] = 0; // 루돌프의 현재 위치 지우기
            ri += rdi;
            rj += rdj;
            v[ri][rj] = -1; // 루돌프의 새로운 위치

            // [1-3] 루돌프와 산타가 충돌한 경우
            if (ri == si && rj == sj) {
                score[mnIdx] += C; // 산타는 C점 획득
                wakeupTurn[mnIdx] = turn + 2; // 깨어날 턴 저장
                moveSanta(mnIdx, si, sj, rdi, rdj, C); // 산타 이동
            }

            // [2-1] 산타 이동
            for (int idx = 1; idx <= P; idx++) {
                if (alive[idx] == 0) continue;
                if (wakeupTurn[idx] > turn) continue;

                si = santa[idx][0];
                sj = santa[idx][1];
                int mnDist = (ri - si) * (ri - si) + (rj - sj) * (rj - sj);
                List<int[]> tlst = new ArrayList<>();

                // 상, 우, 하, 좌로 이동 가능한 위치 탐색
                int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
                for (int[] dir : directions) {
                    int ni = si + dir[0];
                    int nj = sj + dir[1];
                    int dist = (ri - ni) * (ri - ni) + (rj - nj) * (rj - nj);
                    if (ni >= 0 && ni < N && nj >= 0 && nj < N && v[ni][nj] <= 0 && mnDist > dist) {
                        mnDist = dist;
                        tlst.add(new int[]{ni, nj, dir[0], dir[1]});
                    }
                }

                if (tlst.isEmpty()) continue;
                int[] move = tlst.get(tlst.size() - 1);
                int ni = move[0], nj = move[1], di = move[2], dj = move[3];

                // [2-2] 루돌프와 충돌 시 처리
                if (ri == ni && rj == nj) {
                    score[idx] += D;
                    wakeupTurn[idx] = turn + 2;
                    v[si][sj] = 0;
                    moveSanta(idx, ni, nj, -di, -dj, D);
                } else {
                    v[si][sj] = 0;
                    v[ni][nj] = idx;
                    santa[idx][0] = ni;
                    santa[idx][1] = nj;
                }
            }

            // [3] 점수 획득: 살아있는 산타는 1점 추가
            for (int i = 1; i <= P; i++) {
                if (alive[i] == 1) {
                    score[i]++;
                }
            }
        }

        // 최종 결과 출력
        for (int i = 1; i <= P; i++) {
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    // Deque로 산타를 이동 처리
    public static void moveSanta(int cur, int si, int sj, int di, int dj, int mul) {
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{cur, si, sj, mul});

        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            cur = current[0];
            si = current[1];
            sj = current[2];
            mul = current[3];

            int ni = si + di * mul;
            int nj = sj + dj * mul;

            if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                if (v[ni][nj] == 0) {
                    v[ni][nj] = cur;
                    santa[cur][0] = ni;
                    santa[cur][1] = nj;
                    return;
                } else {
                    deque.offer(new int[]{v[ni][nj], ni, nj, 1});
                    v[ni][nj] = cur;
                    santa[cur][0] = ni;
                    santa[cur][1] = nj;
                }
            } else {
                alive[cur] = 0;
                return;
            }
        }
    }
}
