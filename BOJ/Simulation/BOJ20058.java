import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058 {

    static int N;
    static int Q;

    static int mapSize;

    // 해당 회자 파이어스톰 실행 레벨
    static int level;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    // 남아있는 얼음의 합
    static int count;
    // 남아있는 얼음 중 가장 큰 덩어리 칸 갯수
    static int max;

    // 얼음 정보를 담은 맵
    static int[][] map;
    // 회전 및 얼음 녹이기에 사용할 임시 복사본
    static int[][] copy;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        N = Integer.parseInt(tokenizer.nextToken());
        Q = Integer.parseInt(tokenizer.nextToken());

        // 2의 N 제곱 사이즈
        mapSize = (int) Math.pow(2, N);

        map = new int[mapSize][mapSize];
        copy = new int[mapSize][mapSize];

        // 맵 정보 입력
        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // 레벨 정보 입력
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        // Q번 반복
        for (int i = 0; i < Q; i++) {
            level = Integer.parseInt(tokenizer.nextToken());

            // 격자 크기
            int gridSize = (int) Math.pow(2, level);

            // 격자로 회전
            for (int r = 0; r < mapSize; r += gridSize) {
                for (int c = 0; c < mapSize; c += gridSize) {
                    rotation(gridSize, r, c);
                }
            }

            // 격자로 회전한 맵 정보 복사
            copy();

            // 주변 탐색 후 얼음 양 1씩 감소
            meltIce();
        }

        checkIce();

        System.out.println(count);
        System.out.println(max);
    }

    static Queue<int[]> queue;
    static boolean[][] visited;

    static void checkIce() {
        queue = new ArrayDeque<>();
        visited = new boolean[mapSize][mapSize];

        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
                // 얼음 총합
                count += map[r][c];

                if (map[r][c] > 0 && !visited[r][c]) {
                    queue.add(new int[] { r, c });
                    visited[r][c] = true;
                    bfs();
                }
            }
        }
    }

    static void bfs() {
        // 연결되어 있는 얼음이 0개 이상
        int cubeCnt = 0;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            cubeCnt++;

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (isOutOfIndex(nr, nc) || visited[nr][nc])
                    continue;

                if (map[nr][nc] <= 0)
                    continue;

                queue.add(new int[] { nr, nc });
                visited[nr][nc] = true;
            }

        }

        // 가장 큰 덩어리 사이즈 체크
        max = Math.max(cubeCnt, max);
    }

    // 90도로 맵 돌리기
    static void rotation(int size, int startR, int startC) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                copy[r + startR][c + startC] = map[size - (c + 1) + startR][r + startC];
            }
        }
    }

    // 현재 상태 맵 복사
    static void copy() {
        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
                map[r][c] = copy[r][c];
            }
        }
    }

    static void meltIce() {
        // 얼음이 1개 이상 있는 칸을 세는 변수
        int cubeCount = 0;
        int nr = 0, nc = 0;

        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
                cubeCount = 0;

                // 어차피 0이면 감소 필요 X
                if (map[r][c] <= 0)
                    continue;

                // 4방향을 돌면서
                for (int d = 0; d < 4; d++) {
                    nr = r + dr[d];
                    nc = c + dc[d];

                    // 범위를 벗어나면 continue
                    if (isOutOfIndex(nr, nc))
                        continue;

                    // 만약, 얼음이 1개 이상 있다면
                    if (map[nr][nc] > 0)
                        cubeCount++;
                }

                // 얼음이 있는 칸 3개 이상과 인접해있는지 확인
                // 그렇지 않다면 얼음의 양 1 감소
                // 얼음은 한 번에 녹아야 하므로 copy에 먼저 감소
                if (cubeCount < 3)
                    copy[r][c]--;
            }
        }

        // 얼음 녹이기가 끝난 후 map 원본에 복사
        // 얼음 한 번에 녹이기
        copy();
    }

    static boolean isOutOfIndex(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= mapSize || nc >= mapSize;
    }

}
