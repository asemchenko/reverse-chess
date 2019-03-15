package model.chess.moveCheckers;

import model.chess.chessboard.Chessboard;
import model.chess.Move;
import model.chess.chessboard.Position;
import model.chess.chessmans.Chessman;
import model.chess.chessmans.Pawn;
import model.chess.exceptions.ChessException;

import java.util.Objects;

public class ReachablePositionChecker extends MoveChecker {
    private Chessboard board;

    public ReachablePositionChecker(Chessboard board) {
        this.board = board;
    }

    @Override
    protected void validate(Move move) throws ChessException {
        Chessman c = board.get(move.getSrcPosition());
        // TODO придумай как убрать instanceof
        if (c instanceof Pawn) {

        } else
            checkRouteOrThrow(move);
    }

    // отдельная реализация для пешки потому, что пешка бьет не по такой же траектории как обычный ход
    private void checkPawn(Move move) throws ChessException {
        Pawn pawn = (Pawn) board.get(move.getSrcPosition());
        Chessman enemy = board.get(move.getDstPosition());
        // если этот ход - захват пешкой фигуры
        if (Objects.nonNull(enemy) && pawn.canCapture(move.getDstPosition())) {

        } else { // проверяем обычный ход пешки
            checkRouteOrThrow(move);
        }
    }

    private boolean checkRoute(Move move) throws ChessException {
        var src = move.getSrcPosition();
        var dst = move.getDstPosition();

        var chessman = board.get(src);
        var route = chessman.getRouteTo(dst);
        if (Objects.isNull(route)) return false;

        for (Position p : route) {
            if (board.get(p) != null) return false;
        }
        return true;
    }

    private void checkRouteOrThrow(Move move) throws ChessException {
        if (!checkRoute(move)) {
            throw new ChessException("Chessman can not go in a such way");
        }
    }
}
