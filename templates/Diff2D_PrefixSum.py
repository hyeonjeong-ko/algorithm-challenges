class RectangleDiff2D:
    def __init__(self, n: int, m: int):
        """
        2D 차분 배열 초기화 (크기 n x m 보드를 위한 (n+1)x(m+1) 배열)
        :param n: 보드 행 수
        :param m: 보드 열 수
        """
        self.n = n
        self.m = m
        # (n+1) x (m+1) 크기의 0으로 초기화된 차분 배열
        self.diff = [[0] * (m + 1) for _ in range(n + 1)]

    def apply(self, r1: int, c1: int, r2: int, c2: int, delta: int):
        """
        사각형 (r1, c1) ~ (r2, c2)에 delta 더하기 (O(1) 업데이트)
        :param r1: 시작 행
        :param c1: 시작 열
        :param r2: 종료 행
        :param c2: 종료 열
        :param delta: 더할 값 (공격이면 -degree, 회복이면 +degree)
        """
        self.diff[r1][c1] += delta
        self.diff[r2 + 1][c1] -= delta
        self.diff[r1][c2 + 1] -= delta
        self.diff[r2 + 1][c2 + 1] += delta

    def build(self):
        """
        2단계 누적합으로 실제 변화량 계산
        1) 세로 누적합, 2) 가로 누적합
        """
        # 1) 세로 누적합
        for i in range(1, self.n + 1):
            for j in range(self.m + 1):
                self.diff[i][j] += self.diff[i - 1][j]
        # 2) 가로 누적합
        for i in range(self.n + 1):
            for j in range(1, self.m + 1):
                self.diff[i][j] += self.diff[i][j - 1]

    def get(self, i: int, j: int) -> int:
        """
        (i, j) 좌표의 최종 변화량을 반환
        """
        return self.diff[i][j]
