package model.chess;

import model.chess.chessboard.Chessboard;
import model.chess.chessmans.ChessmanColor;
import model.chess.chessmans.factory.BlackFactory;
import model.chess.chessmans.factory.WhiteFactory;
import model.chess.exceptions.ChessException;
import model.chess.moveCheckers.*;
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
        moveChecker = new ChessmanExistsChecker(board);
        moveChecker
                .setSuccessor(new FigureColorChecker(this::getCurrentUserColor, board))
                .setSuccessor(new DestinationChecker(board))
                .setSuccessor(new RightEnemyChecker(board))
                .setSuccessor(new RouteChecker(board))
                .setSuccessor(new MustCaptureChecker(board));
        chessboardSerializer = new ChessboardSerializer(board);
    }

    public void processMove(Move move) throws ChessException {
        // throws exception if move is invalid
        moveChecker.check(move);
        // if move is valid
        //board.move(move.getSrcPosition(), move.getDstPosition());
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