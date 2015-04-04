package misc;

import org.junit.Test;

public class RotateImage {

    @Test
    public void try1 () {
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        rotate (a);
    }

    /*
     * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise). Follow up: Could you do this in-place?
     */
    public void rotate (int[][] matrix) {
        if (matrix == null)
            return;
        int column = matrix[0].length;
        int row = matrix.length;
        int i = 0;
        int diff = 0;
        while (i < row / 2) {
            for (int j = 0 + diff; j < (column - diff); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[row - diff - 1][j];
                matrix[row - diff - 1][j] = matrix[row - diff - 1][column - diff - 1];
                matrix[row - diff - 1][column - diff - 1] = matrix[i][column - diff - 1];
                matrix[i][column - diff - 1] = temp;
            }
            diff++;
            i++;
        }
    }
}
