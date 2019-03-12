package model.chess;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Position {
    private static final List<Position> ALL_POSITIONS;

    static {
        List<Position> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                list.add(new Position(i, j));
            }
        }
        ALL_POSITIONS = Collections.unmodifiableList(list);
    }

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

    public static Collection<Position> getAllPossiblePositions() {
        return ALL_POSITIONS;
    }

    public int getCharPosition() {
        return charPosition;
    }

    public void setCharPosition(int charPosition) {
        // FIXME hardcoded 8
        // TODO add check: charPosition must be greater than 0
        if (charPosition <= 0 || charPosition > 8) {
            throw new IndexOutOfBoundsException("Invalid char value of position: " + charPosition);
        }
        this.charPosition = charPosition;
    }

    public int getNumericPosition() {
        return numericPosition;
    }

    public void setNumericPosition(int numericPosition) {
        // FIXME hardcoded 8
        // TODO add check: numeric must be greater than 0
        if (numericPosition <= 0 || numericPosition > 8) {
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

    public void move(int charOffset, int numericOffset) {
        setNumericPosition(getNumericPosition() + numericOffset);
        setCharPosition(getCharPosition() + charOffset);
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
        return String.format("{Position %c:%d}", 'a' + getCharIndex(), getNumericPosition());
    }

    /* TODO add getters
     *  getNumericIndex
     *  getCharIndex
     */
}
