package model.chess.moveCheckers;

import model.chess.Move;
import model.chess.chessboard.Chessboard;
import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RouteChecker extends MoveChecker {
    private Chessboard board;

    public RouteChecker(Chessboard board) {
        this.board = board;
    }

    @Override
    protected void validate(Move move) throws ChessException {
        if (!checkRoute(move)) {
            throw new ChessException("Chessman can not jump over another figure");
        }
    }

    public boolean validateNoThrow(Move move) {
        try {
            return checkRoute(move);
        } catch (ChessException e) {
            return false;
        }
    }

    private boolean checkRoute(Move move) throws ChessException {
        var src = move.getSrcPosition();
        var dst = move.getDstPosition();

        var chessman = board.get(src);
        var enemy = board.get(dst);
        if (Objects.nonNull(enemy)) {
            return traceRoute(chessman.getRouteCaptureMove(dst));
        } else {
            return traceRoute(chessman.getRouteSimpleMove(dst));
        }
    }

    private boolean traceRoute(@NotNull Iterable<Position> route) {
        for (Position p : route) {
            if (board.get(p) != null) return false;
        }
        return true;
    }
}
