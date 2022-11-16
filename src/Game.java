import java.util.Objects;
import java.util.Scanner;

public class Game {
    public void play() {

        Scanner in = new Scanner(System.in);
        String[][] options = new String[6][7];
        for (int i = 0; i < options.length; ++i) {
            for (int j = 0; j < options[0].length; ++j) {
                options[i][j] = " ";
            }
        }
        int turn = 1;
        String player = "\u001B[35m" + "O" + "\u001B[0m";
        boolean winner = false;
        while (!winner && turn <= 42) {
            board(options);
            System.out.print("Player " + player + ", choose a column: ");
            int userChoice = in.nextInt();
            valid(userChoice, options);
            if (player.equals("\u001B[35m" + "O" + "\u001B[0m")) {
                player = "\u001B[33m" + "O" + "\u001B[0m";
            } else {
                player = "\u001B[35m" + "O" + "\u001B[0m";
            }
            for (int i = options.length - 1; i >= 0; --i) {
                if (options[i][userChoice].equals(" ")) {
                    options[i][userChoice] = player;
                    break;
                }
            }
            winner = isWinner(player, options);
            turn++;
        }
        board(options);
        if (isWinner(player, options)) {
            if (player.equals("\u001B[35m" + "O" + "\u001B[0m")) {
                System.out.println("Player purple win");
            } else {
                System.out.println("Player yellow win");
            }
        } else {
            System.out.println("Draw");
        }
    }

    public void board(String[][] options) {

        System.out.println(" 0 1 2 3 4 5 6");
        for (int i = 0; i < options.length; ++i) {
            System.out.print("|");
            for (int j = 0; j < options[0].length; ++j) {
                System.out.print(options[i][j]);
                System.out.print("|");
            }
            System.out.println();

        }
        System.out.println();
    }

    public static void valid(int column, String[][] options) {

        if (column < 0 || column > options[0].length) {
            System.out.println("That column doesn't exist");
        }

        if (!Objects.equals(options[0][column], " ")) {
            System.out.println("That column is full, choose another one");
        }
    }

    public static boolean isWinner(String player, String[][] options) {

        for (int i = 0; i < options.length; ++i) {
            for (int j = 0; j < options[0].length - 3; ++j) {
                if (options[i][j].equals(player) &&
                        options[i][j + 1].equals(player) &&
                        options[i][j + 2].equals(player) &&
                        options[i][j + 3].equals(player)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < options.length - 3; ++i) {
            for (int j = 0; j < options[0].length; ++j) {
                if (options[i][j].equals(player) &&
                        options[i + 1][j].equals(player)&&
                        options[i + 2][j].equals(player) &&
                        options[i + 3][j].equals(player)) {
                    return true;
                }
            }
        }

        for (int i = 3; i < options.length; ++i) {
            for (int j = 0; j < options[0].length - 3; ++j) {
                if (options[i][j].equals(player) &&
                        options[i - 1][j + 1].equals(player) &&
                        options[i - 2][j + 2].equals(player) &&
                        options[i - 3][j + 3].equals(player)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < options.length - 3; ++i) {
            for (int j = 0; j < options[0].length - 3; ++j) {
                if (options[i][j].equals(player) &&
                        options[i + 1][j + 1].equals(player) &&
                        options[i + 2][j + 2].equals(player) &&
                        options[i + 3][j + 3].equals(player)) {
                    return true;
                }
            }
        }
        return false;
    }
}
