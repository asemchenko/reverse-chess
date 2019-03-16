package model.chess;

import model.chess.chessboard.Chessboard;
import model.chess.chessmans.ChessmanColor;
import model.chess.chessmans.factory.BlackFactory;
import model.chess.chessmans.factory.WhiteFactory;
import model.chess.exceptions.ChessException;
import model.chess.moveCheckers.FigureColorChecker;
import model.chess.moveCheckers.MoveChecker;
import model.chess.moveCheckers.ReachablePositionChecker;
import model.chess.moveCheckers.RightEnemyChecker;
import model.chess.serialization.ChessboardSerializer;
import model.chess.serialization.SerializedBoard;
import org.jetbrains.annotations.Contract;

// TODO нужно реализовать проверку хода - не должен ли игрок сейчас бить
public class ChessLogic {
    private final Chessboard board;
    private ChessmanColor currentUserColor = ChessmanColor.WHITE;
    private MoveChecker moveChecker;
    private ChessboardSerializer chessboardSerializer;

    public ChessLogic() {
        board = new ChessboardFiller(new WhiteFactory(), new BlackFactory(),
                new Chessboard())
                .fill();
        moveChecker = new FigureColorChecker(this::getCurrentUserColor, board);
        moveChecker.setSuccessor(new ReachablePositionChecker(board))
                .setSuccessor(new RightEnemyChecker(board));
        chessboardSerializer = new ChessboardSerializer(board);
    }

    public void processMove(Move move) throws ChessException {
        // throws exception if move is invalid
        moveChecker.check(move);
        // if move is valid
        board.move(move.getSrcPosition(), move.getDstPosition());
        currentUserColor = currentUserColor.getOpposite();
    }

    public SerializedBoard getBoard() {
        return chessboardSerializer.serialize();
    }

    @Contract(pure = true)
    private ChessmanColor getCurrentUserColor() {
        return currentUserColor;
    }
}