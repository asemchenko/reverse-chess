package temp;

import model.chess.ChessLogic;
import model.chess.Move;
import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;
import model.chess.serialization.SerializedBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ChessLogic chessLogic = new ChessLogic();
        while (true) {
            printBoard(chessLogic.getBoard());
            var move = readMove();
            try {
                chessLogic.processMove(move);
            } catch (ChessException e) {
                System.err.println("Error :" + e);
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    private static void printBoard(SerializedBoard board) {
        var lines = new ArrayList<>(board.getLines());
        Collections.reverse(lines);
        int i = 8;
        System.out.println("  a b c d e f g h");
        System.out.println("  ________________");
        for (var l : lines) {
            System.out.println(i + "|" + l + "|" + i);
            --i;
        }
        System.out.println("  ________________");
        System.out.println("  a b c d e f g h");
    }

    private static Move readMove() {
        System.out.println();
        System.out.print("Input move: ");
        System.out.flush();
        String input = scanner.next();
        var pos = input.split("-");
        return new Move(new Position(pos[0]), new Position(pos[1]));
    }
}
