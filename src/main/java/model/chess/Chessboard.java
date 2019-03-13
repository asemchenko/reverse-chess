package model.chess;

import model.chess.chessmans.Chessman;
import model.chess.exceptions.ChessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Chessboard {
    private Chessman[][] board;
    private ChessmanColor currentUserColor;

    public void processMove(Move move) throws ChessException {
        checkForIllegalUser(move);
        if (checkMoveRoute(move) && checkForRightEnemy(move)) {
            move(move);
        } else {
            throw new ChessException("Invalid move");
        }
    }

    private boolean checkMoveRoute(@NotNull Move move) {
        var src = move.getSrcPosition();
        var dst = move.getDstPosition();
        var chessman = get(src);
        if (Objects.isNull(chessman)) return false;
        var route = chessman.getRouteTo(dst);
        if (Objects.isNull(route)) return false;
        for (Position p : route) {
            if (get(p) != null) return false;
        }
        return true;
    }

    private void checkForIllegalUser(@NotNull Move move) throws ChessException {
        Chessman chessman = get(move.getSrcPosition());
        if (Objects.nonNull(chessman) && !chessman.getColor().equals(currentUserColor)) {
            throw new ChessException(String.format("%s are going now", currentUserColor));
        }
    }

    private boolean checkForRightEnemy(@NotNull Move move) {
        Chessman chessman = get(move.getSrcPosition());
        Chessman enemy = get(move.getDstPosition());
        return Objects.isNull(enemy) || enemy.getColor() != chessman.getColor();
    }

    private void move(@NotNull Move move) {
        set(move.getDstPosition(), get(move.getSrcPosition()));
        set(move.getSrcPosition(), null);
    }

    @Nullable
    public Chessman get(@NotNull Position position) {
        return board[position.getCharIndex()][position.getNumericIndex()];
    }

    public void set(@NotNull Position position, Chessman value) {
        board[position.getCharPosition()][position.getNumericPosition()] = value;
    }

}
