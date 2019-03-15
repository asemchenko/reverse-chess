package model.chess.chessboard;

import model.chess.chessmans.Chessman;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Container for chessmans
 */
public class Chessboard {
    private Chessman[][] board;

    public Chessboard() {
        createBoard();
    }

    private void createBoard() {
        board = new Chessman[8][8];
    }

    /**
     * Move chessman placed at <code>src</code> to
     * <code>dst</code>.
     * After this operation <code>src</code> cell
     * becomes empty.
     * Move can be processed even if <code>dst</code>
     * cell is not empty.
     * Nothing happens when <code>src</code> and
     * <code>dst</code> positions are equals.
     *
     * @param src source position
     * @param dst destination
     */
    public void move(Position src, Position dst) {
        Chessman c = get(src);
        set(src, null);
        set(dst, c);
    }

    /**
     * Returns chessman that is placed on specified cell
     *
     * @param position cell position
     * @return chessman that is placed on <code>position</code>.
     * If cell is empty - returns null
     */
    @Nullable
    public Chessman get(@NotNull Position position) {
        return board[position.getCharIndex()][position.getNumericIndex()];
    }

    /**
     * Place chessman on the board at specified position.
     *
     * @param position position
     * @param value    chessman
     */
    public void set(@NotNull Position position, Chessman value) {
        value.setPosition(position);
        board[position.getCharPosition()][position.getNumericPosition()] = value;
    }

    /**
     * Removes every chessman from the board
     */
    public void clear() {
        createBoard();
    }
}
