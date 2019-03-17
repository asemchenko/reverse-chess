package model.chess;

import model.chess.chessboard.Chessboard;
import model.chess.chessboard.Position;
import model.chess.chessmans.factory.ChessmanFactory;
import org.jetbrains.annotations.NotNull;

public class ChessboardFiller {
    private ChessmanFactory whiteFactory;
    private ChessmanFactory blackFactory;
    private Chessboard board;

    public ChessboardFiller(@NotNull ChessmanFactory whiteFactory,
                            @NotNull ChessmanFactory blackFactory,
                            @NotNull Chessboard board) {
        this.whiteFactory = whiteFactory;
        this.blackFactory = blackFactory;
        this.board = board;
        board.clear();
    }

    public Chessboard fill() {
        // adding white figures
        fillFirstLine(whiteFactory, 1);
        fillSecondLine(whiteFactory, 2);
        // adding black figures
        fillFirstLine(blackFactory, 8);
        fillSecondLine(blackFactory, 7);
        return board;
    }


    private void fillFirstLine(ChessmanFactory f, int horizontal) {
        board.put(new Position('a', horizontal), f.createRook());
        board.put(new Position('b', horizontal), f.createKnight());
        board.put(new Position('c', horizontal), f.createBishop());
        board.put(new Position('d', horizontal), f.createQueen());
        board.put(new Position('e', horizontal), f.createKing());
        board.put(new Position('f', horizontal), f.createBishop());
        board.put(new Position('g', horizontal), f.createKnight());
        board.put(new Position('h', horizontal), f.createRook());
    }

    private void fillSecondLine(ChessmanFactory f, int horizontal) {
        int charD = 1;
        int numD = 0;
        Position p = new Position('a', horizontal);
        for (; p.canBeMoved(charD, numD); p = p.move(charD, numD)) {
            board.put(p, f.createPawn());
        }
        board.put(p, f.createPawn());
    }
}
