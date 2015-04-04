package misc;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

public class SuroundRegion {

    /*
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that
     * surrounded region. For example, X X X X X O O X X X O X X O X X After running your function, the board should be: X X X X X X X X X X X X X O X
     * X
     */
    @Test
    public void try1 () {
        char[][] b2 = new char[1][1];
        b2[0][0]='O';
        
        solve(b2);
    }

    public void solve (char[][] board) {
        int row = board.length;
        int column = board[0].length;
        boolean[][] b2 = new boolean[row][column];
        for (int i = 0; i < column; i++) {
            if (board[0][i] == 'O') {
                bfs (0, i, board, b2);
            }
            if (board[row - 1][i] == 'O') {
                bfs (row - 1, i, board, b2);
            }
        }
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                bfs (i, 0, board, b2);
            }
            if (board[i][column - 1] == 'O') {
                bfs (i, column - 1, board, b2);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 'O' && !b2[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs (int x, int y, char[][] board, boolean[][] b2) {
        int row = board.length;
        int column = board[0].length;
        Queue <Cor> a = new LinkedList <> ();
        a.add (new Cor (x, y));
        while (!a.isEmpty ()) {
            Cor tc = a.poll ();
            if (board[tc.x][tc.y] == 'O') {
                b2[tc.x][tc.y] = true;
            }
            if (tc.x - 1 >= 0 && board[tc.x - 1][tc.y] == 'O'&&!b2[tc.x - 1][tc.y]) {
                b2[tc.x - 1][tc.y] = true;
                a.add (new Cor (tc.x - 1, tc.y));
            }
            if (tc.x + 1 < row && board[tc.x + 1][tc.y] == 'O'&&!b2[tc.x + 1][tc.y]) {
                b2[tc.x + 1][tc.y] = true;
                a.add (new Cor (tc.x + 1, tc.y));
            }
            if (tc.y - 1 >= 0 && board[tc.x][tc.y - 1] == 'O'&&!b2[tc.x][tc.y - 1]) {
                b2[tc.x][tc.y - 1] = true;
                a.add (new Cor (tc.x, tc.y - 1));
            }
            if (tc.y + 1 < column && board[tc.x][tc.y + 1] == 'O'&& !b2[tc.x][tc.y + 1]) {
                b2[tc.x][tc.y + 1] = true;
                a.add (new Cor (tc.x, tc.y + 1));
            }
        }
    }

    public class Cor {

        public int x;
        public int y;

        Cor (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
