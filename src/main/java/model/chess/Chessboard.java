package model.chess;

import model.chess.exceptions.ChessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Chessboard {
    private Chessman[][] board;
    private ChessmanColor currentUserColor;

    public void processMove(Move move) throws ChessException {
        checkForEmptyCell(move);
        checkForIllegalUser(move);
        Chessman chessman = get(move.getSrcPosition());
        if (chessman.canBeMoved(move.getDstPosition())) {
            chessman.move(move.getDstPosition());
        } else {
            throw new ChessException(String.format("%s can not be moved in a such way", chessman));
        }
    }

    private void checkForIllegalUser(@NotNull Move move) throws ChessException {
        Chessman chessman = get(move.getSrcPosition());
        if (Objects.nonNull(chessman) && !chessman.getColor().equals(currentUserColor)) {
            throw new ChessException(String.format("%s are going now", currentUserColor));
        }
    }

    private void checkForEmptyCell(@NotNull Move move) throws ChessException {
        Chessman chessman = get(move.getSrcPosition());
        if (Objects.isNull(chessman)) {
            throw new ChessException("Invalid move. There isn't any chessman at " + move.getSrcPosition() + " position");
        }
    }

    @Nullable
    private Chessman get(@NotNull Position position) {
        // FIXME invalid indexation. Should be postion.getIndex() instead of getPosition()
        return board[position.getCharPosition()][position.getNumericPosition()];
    }

    private void set(@NotNull Position position, Chessman value) {
        board[position.getCharPosition()][position.getNumericPosition()] = value;
    }

    public abstract class Chessman {
        // TODO реализуй во всех фигурах проверку, не бьет ли фигура свою
        protected ChessmanColor color;
        protected Position position;

        public Chessman(ChessmanColor color, Position position) {
            this.color = color;
            this.position = position;
        }

        public ChessmanColor getColor() {
            return color;
        }

        protected Chessman get(Position position) {
            return Chessboard.this.get(position);
        }

        protected void set(Position position, Chessman value) {
            Chessboard.this.set(position, value);
        }

        public abstract boolean canBeMoved(Position dst);

        protected void move(Position dst) throws ChessException {
            if (canBeMoved(dst)) {
                set(position, null);
                set(dst, this);
                position = dst;
            } else {
                throw new ChessException("Illegal move");
            }
        }

        /**
         * Checks that at destination position placed right enemy:
         * <ul>
         * <li>The opposite color of this chessman</li>
         * <li>Destination cell is empty</li>
         * </ul>
         *
         * @param dst destination position
         * @return true if at <code>dst</code> placed right enemy, false otherwise
         */
        protected boolean checkForRightEnemy(Position dst) {
            Chessboard.Chessman enemy = get(dst);
            return Objects.isNull(enemy) || enemy.getColor() != getColor();
        }
    }
}
