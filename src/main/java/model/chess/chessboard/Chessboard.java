package model.chess.chessboard;

import model.chess.chessmans.Chessman;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        return board[position.getNumericIndex()][position.getCharIndex()];
    }

    /**
     * Place chessman on the board at specified position.
     *
     * @param position position
     * @param value    chessman
     */
    public void set(@NotNull Position position, Chessman value) {
        if (Objects.nonNull(value)) {
            value.setPosition(position);
        }
        board[position.getNumericIndex()][position.getCharIndex()] = value;
    }

    public List<List<Chessman>> asList() {
        List<List<Chessman>> b = new ArrayList<>(8);
        for (var l : board) {
            b.add(Arrays.asList(l));
        }
        return b;
    }

    /**
     * Removes every chessman from the board
     */
    public void clear() {
        createBoard();
    }
}
