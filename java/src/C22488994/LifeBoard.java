package C22488994;
import processing.core.PApplet;

public class LifeBoard {
    boolean[][] board;
    boolean[][] next;
    int size;
    float cellSize;
    PApplet parent;
    int[][] colors;

    public LifeBoard(int size, PApplet parent) {
        this.size = size;
        this.parent = parent;
        this.cellSize = (float) parent.width / size;
        this.board = new boolean[size][size];
        this.next = new boolean[size][size];
        this.colors = new int[size][size];
        randomize();
        randomizeColors();
    }

    public void randomize() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = parent.random(1.0f) > 0.5f;
            }
        }
    }

    public void randomizeColors() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                colors[row][col] = parent.color(parent.random(255), parent.random(255), parent.random(255));
            }
        }
    }

    public void update() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int count = countCellsAround(row, col);
                if (board[row][col]) {
                    if (count == 2 || count == 3) {
                        next[row][col] = true;
                    } else {
                        next[row][col] = false;
                    }
                } else {
                    if (count == 3) {
                        next[row][col] = true;
                    } else {
                        next[row][col] = false;
                    }
                }
            }
        }
        boolean[][] temp = board;
        board = next;
        next = temp;
        updateColors();
    }

    public void updateColors() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int count = countCellsAround(row, col);
                if (board[row][col]) {
                    colors[row][col] = parent.color(255); // Set color to white for live cells
                } else {
                    // Calculate color based on neighboring cells
                    int averageColor = 0;
                    int countAliveNeighbors = count;
                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = col - 1; j <= col + 1; j++) {
                            if (i >= 0 && i < size && j >= 0 && j < size && !(i == row && j == col) && board[i][j]) {
                                averageColor += colors[i][j];
                            } else {
                                countAliveNeighbors--;
                            }
                        }
                    }
                    if (countAliveNeighbors > 0) {
                        colors[row][col] = averageColor / countAliveNeighbors;
                    }
                }
            }
        }
    }

    public int countCellsAround(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size && !(i == row && j == col) && board[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void setAlive(int row, int col, boolean alive) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            board[row][col] = alive;
        }
    }

    public boolean isAlive(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return board[row][col];
        } else {
            return false;
        }
    }

    public void render() {
        parent.background(0);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                parent.fill(colors[row][col]);
                parent.rect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }
}
