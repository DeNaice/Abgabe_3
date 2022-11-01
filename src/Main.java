/* Game 2048 by @denkspuren, 2014-09-11, 11:47
   New BSD License: http://opensource.org/licenses/BSD-3-Clause

   This is an implementation of 2048 without animation effects.

   Use arrow keys (left, right, up and down) to move tiles on the
   grid. Game ends if the tiles cannot be moved anymore.

   For the game and its history see e.g. Wikipedia:
   http://en.wikipedia.org/wiki/2048_(video_game)
   http://de.wikipedia.org/wiki/2048_(Computerspiel)
*/

public static void main(String[] args){}

import java.util.Arrays;

int[] grid = new int[16]; // default values are 0
int score = 0;
boolean game = true;

final int X_POS = 0;
final int Y_POS = 0;
final int X_OFFSET = 20;
final int Y_OFFSET = 20;

final int SIZE_TILE = 80;
final int SIZE_BORDER = 10;

int free_slots(int[] grid) {
  int i = 0;
  for (int val : grid) {
    if (val == 0) i++;
  }
  return i;
}

/  0  1  2  3     3  7 11 15     +12 +7  +2  −3  → −5
    4  5  6  7     2  6 10 14     +9  +4  −1  −6  → −5
    8  9 10 11     1  5  9 13     +6  +1  −4  −9  → −5
   12 13 14 15     0  4  8 12     +3  −2  −7  −12 → −5
                                   |   |   |   |
                                   V   V   V   V
                                  −3  −3  −3  −3
 /

void rotate(int[] grid) {
  int[] temp_grid = new int[grid.length];
  for (int i=0; i

    temp_grid[i+12-(i%4)*5-(i/4)*3] = grid[i];
  }
  arrayCopy(temp_grid,grid);
}

void rotate(int[] grid, int n) {
  for (int i=1; i<=(n%4); i++) { rotate(grid); }
}

void shift(int[] grid) {
  int offset=0;
  for (int i=0; i

    if (i%4 == 0) {
      offset = 0;
    }
    if (grid[i] == 0) {
      offset++;
    } else if (offset > 0) {
      grid[i-offset] = grid[i];
      grid[i] = 0;
    }
  }
}

int move(int[] grid) {
  int score = 0;
  shift(grid);
  score = merge(grid);
  shift(grid);
  return score;
}

boolean is_game_over(int[] grid) {
  int[] temp_grid = new int[grid.length];
  arrayCopy(grid, temp_grid);
  for (int i=1; i<=4; i++) {
    move(temp_grid); rotate(temp_grid);
  }
  return Arrays.equals(temp_grid,grid);
}

int merge(int[] grid) {
  int score = 0;
  for (int i=0; i

    if (i%4 < 3) {
      if (grid[i] > 0 && grid[i] == grid[i+1]) {
        grid[i] += grid[i+1];
        grid[i+1] = 0;
        score += grid[i];
      }
    }
  }
  return score;
}

void insert_tile(int[] grid, int n, int val) {
  for (int i=0; i

    if (grid[i] == 0) {
      if (n == 0) {
        grid[i] = val;
        break;
      }
      n—;
    }
  }
}

void show(int[] grid) {
  int edge_length = int(sqrt(grid.length));
  int i = 0;
  int X, Y;
  for (int y=0; y

    Y = Y_POS+Y_OFFSET+SIZE_BORDER+y(SIZE_TILE+SIZE_BORDER);
    for (int x=0; x

      X = X_POS+X_OFFSET+SIZE_BORDER+x*(SIZE_TILE+SIZE_BORDER);
      // fill(color(179, 189, 214));
      fill(color(30+log(grid[i]+1)/log(2)10, 100, 100));
      rect(X, Y, SIZE_TILE, SIZE_TILE, 15);
      if (grid[i] != 0) {
        fill(color(271, 0, 1));
        text(grid[i], X+SIZE_TILE/2+1, Y+SIZE_TILE/2+1);
      }
      i++;
    }
  }
}

void random_tile(int[] grid) {
  int pos, val;
  pos = int(random(0, free_slots(grid)));
  val = random(0, 1) < 0.9 ? 2 : 4;
  insert_tile(grid, pos, val);
}

void setup() {
  textAlign(CENTER, CENTER);
  textSize(27);
  noStroke();
  background(color(179, 189, 214));
  colorMode(HSB, 360, 100, 100);
  int X_SIZE = 2*X_POS+2*X_OFFSET+SIZE_BORDER+4(SIZE_TILE+SIZE_BORDER);
  int Y_SIZE = 2*Y_POS+2*Y_OFFSET+SIZE_BORDER+4*(SIZE_TILE+SIZE_BORDER);
  size(X_SIZE, Y_SIZE);
  print(„X_SIZE:“, X_SIZE, „Y_SIZE:“, Y_SIZE);

  random_tile(grid);
  random_tile(grid);
  show(grid);
  println(grid);
}

void keyPressed() {
  int[] temp_grid = new int[grid.length];
  arrayCopy(grid, temp_grid);

  if (key == CODED && game) {
    switch(keyCode) {
      case LEFT:
        score += move(grid);
        break;
      case RIGHT:
        rotate(grid,2);
        score += move(grid);
        rotate(grid,2);
        break;
      case UP:
        rotate(grid);
        score += move(grid);
        rotate(grid,3);
        break;
      case DOWN:
        rotate(grid,3);
        score += move(grid);
        rotate(grid);
    }
  }
  if (!Arrays.equals(grid,temp_grid)) {
    random_tile(grid);
    show(grid);
    println(„SCORE =“,score);
  }
  if (is_game_over(grid)==true) {
    game = false;
    println(„GAME OVER. YOUR SCORE =“,score);
  }
}

void draw() {
}