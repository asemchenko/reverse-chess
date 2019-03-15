package model.chess.chessboard;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

// TODO сделай Position immutable
public final class Position {
    private static List<Position> ALL_POSITIONS;
    private int charPosition;
    private int numericPosition;

    public Position(int charPosition, int numericPosition) {
        setCharPosition(charPosition);
        setNumericPosition(numericPosition);
    }

    public Position(char charPosition, int numericPosition) {
        this(charPosition - 'a' + 1, numericPosition);
    }

    public Position(@NotNull Position that) {
        setCharPosition(that.getCharPosition());
        setNumericPosition(that.getNumericPosition());
    }

    @NotNull
    @Contract("_, _, _ -> new")
    public static Position getMoved(@NotNull Position p, int charOffset, int numOffset) {
        return new Position(p.getCharPosition() + charOffset, p.getNumericPosition() + numOffset);
    }

    public static boolean canBeMoved(@NotNull Position p, int charOffset, int numOffset) {
        return checkCharPosition(p.getCharPosition() + charOffset)
                && checkNumericPosition(p.getNumericPosition() + numOffset);
    }

    public static Collection<Position> getAllPossiblePositions() {
        if (ALL_POSITIONS == null) {
            List<Position> list = new ArrayList<>();
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    list.add(new Position(i, j));
                }
            }
            ALL_POSITIONS = Collections.unmodifiableList(list);
        }
        return ALL_POSITIONS;
    }

    private static boolean checkCharPosition(int charPosition) {
        // charPosition should be in range [1;8]
        return charPosition > 0 && charPosition <= 8;
    }

    private static boolean checkNumericPosition(int numPosition) {
        // numPosition must satisfy such rules as charPosition
        return checkCharPosition(numPosition);
    }

    public int getCharPosition() {
        return charPosition;
    }

    private void setCharPosition(int charPosition) {
        if (!checkCharPosition(charPosition)) {
            throw new IndexOutOfBoundsException("Invalid char value of position: " + charPosition);
        }
        this.charPosition = charPosition;
    }

    public int getNumericPosition() {
        return numericPosition;
    }

    private void setNumericPosition(int numericPosition) {
        if (!checkNumericPosition(numericPosition)) {
            throw new IndexOutOfBoundsException("Invalid numeric value of position: " + numericPosition);
        }
        this.numericPosition = numericPosition;
    }

    public int charDistance(@NotNull Position other) {
        return Math.abs(this.charSubstract(other));
    }

    public int numericDistance(@NotNull Position other) {
        return Math.abs(this.numericSubstract(other));
    }

    public int charSubstract(@NotNull Position other) {
        return getCharPosition() - other.getCharPosition();
    }

    public int numericSubstract(@NotNull Position other) {
        return getNumericPosition() - other.getNumericPosition();
    }

    public int getCharIndex() {
        return getCharPosition() - 1;
    }

    public int getNumericIndex() {
        return getNumericPosition() - 1;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getCharPosition() == position.getCharPosition() &&
                getNumericPosition() == position.getNumericPosition();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharPosition(), getNumericPosition());
    }

    @Override
    public String toString() {
        return String.format("%c%d", 'a' + getCharIndex(), getNumericPosition());
    }
}
