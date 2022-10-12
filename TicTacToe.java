package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '.';
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = 'O';

    public static Scanner input = new Scanner(System.in);
    public static char[][] gameBroo;

    public static void initGameField() {
        gameBroo = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameBroo[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printFieldGame() {
        for (int i = 0; i <= gameBroo.length; i++) {
            System.out.print(i == 0 ? "  " : i + " ");
        }
        System.out.println();
        for (int i = 0; i < gameBroo.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < gameBroo.length; j++) {
                System.out.print(gameBroo[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean availableСell(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE && gameBroo[y][x] == DOT_EMPTY;
    }

    public static void player1Move() {
        int x, y;
        do {
            System.out.printf("Ввод координат хода через пробел в формате X(= 1..%d) Y(= 1..%d):\n", SIZE, SIZE);
            x = Integer.valueOf(input.next()) - 1;
            y = Integer.valueOf(input.next()) - 1;
        } while (!availableСell(x, y));
        gameBroo[y][x] = DOT_HUMAN;
    }

    public static void playerMove2() {
        int x, y;
        do {
            x = new Random().nextInt(SIZE);
            y = new Random().nextInt(SIZE);
        } while (!availableСell(x, y));
        System.out.println("Комп походил в ячейку " + (x + 1) + " " + (y + 1));
        gameBroo[y][x] = DOT_AI;
    }

    public static boolean isWin(char pointPlayer) {
        int hor, ver;
        int diagMain, diagReverse;
        for (int i = 0; i < SIZE; i++) {
            hor = 0;
            ver = 0;
            for (int j = 0; j < SIZE; j++) {
                if (gameBroo[i][j] ==  pointPlayer) {
                    hor++;
                } else if (gameBroo[i][j] !=  pointPlayer && hor < DOTS_TO_WIN) {
                    hor = 0;
                }
                if (gameBroo[j][i] ==  pointPlayer) {
                    ver++;
                }   else if (gameBroo[j][i] !=  pointPlayer && ver < DOTS_TO_WIN) {
                    ver = 0;
                }
            }
            if (hor >= DOTS_TO_WIN || ver >= DOTS_TO_WIN) {
                System.out.println("По горизонтали или вертикали " + hor + " " + ver);
                return true;
            }
        }

        for (int j = 0; j < SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = j + i;
                if (k < SIZE) {
                    if (gameBroo[i][k] ==  pointPlayer) {
                        diagMain++;
                    } else if (gameBroo[i][k] !=  pointPlayer && diagMain < DOTS_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOTS_TO_WIN) {
                    System.out.println("По главной диагонали от центральной оси вправо " + diagMain);
                    return true;
                }
            }
        }
        for (int j = 1; j < SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = j + i;
                if (k < SIZE) {
                    if (gameBroo[k][i] ==  pointPlayer) {
                        diagMain++;
                    } else if (gameBroo[k][i] !=  pointPlayer && diagMain < DOTS_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOTS_TO_WIN) {
                    System.out.println("По главной диагонали от центральной оси вниз " + diagMain);
                    return true;
                }
            }
        }
        for (int j = 0; j < SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = (SIZE - 1) - i;
                int l = j + i;
                if (k >= 0 && l < SIZE) {
                    if (gameBroo[l][k] ==  pointPlayer) {
                        diagReverse++;
                    } else if (gameBroo[l][k] != pointPlayer && diagReverse < DOTS_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOTS_TO_WIN) {
                    System.out.println("По побочной диагонали от центральной оси вниз " + diagReverse);
                    return true;
                }
            }
        }
        for (int j = 1; j < SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < SIZE; i++) {
                int k = (SIZE - 1) - j - i;
                if (k >= 0) {
                    if (gameBroo[i][k] ==  pointPlayer) {
                        diagReverse++;
                    } else if (gameBroo[i][k] !=  pointPlayer && diagReverse < DOTS_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOTS_TO_WIN) {
                    System.out.println("По побочной диагонали от центральной оси влево " + diagReverse);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDraw() {
        for (char[] aGameField : gameBroo) {
            for (int j = 0; j < gameBroo.length; j++) {
                if (aGameField[j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        initGameField();                    // инициализируем игровое поле - создаём "пустой" двумерный массив
        System.out.printf("Цель игры - заполнить подряд линию по вертикали, горизонтали или диагонали из %d Ваш%s символ%s.\n", DOTS_TO_WIN, (DOTS_TO_WIN % 10 == 1 && DOTS_TO_WIN % 100 != 11) ? "его" : "их", (DOTS_TO_WIN % 10 == 1 && DOTS_TO_WIN % 100 != 11) ? "а" : "ов");
        printFieldGame();                   // выводим состояние начального поля в консоль

        switch (new Random().nextInt(2)) {
            case 0: {
                System.out.println("Ваш ход первый!");
                while (true) {
                    player1Move();
                    printFieldGame();
                    if (isWin(DOT_HUMAN)) {
                        System.out.println("Выиграл Игрок ");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                    playerMove2();
                    printFieldGame();
                    if (isWin(DOT_AI)) {
                        System.out.println("Победил Комп");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                }
                break;
            }
            case 1: {
                System.out.println("Первый ход за Компом!");
                while (true) {
                    playerMove2();
                    printFieldGame();
                    if (isWin(DOT_AI)) {
                        System.out.println("Победил Комп");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                    player1Move();
                    printFieldGame();
                    if (isWin(DOT_HUMAN)) {
                        System.out.println("Победил Игрок ");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                }
            }
        }
    }






}
