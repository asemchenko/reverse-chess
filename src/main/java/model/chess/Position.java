package model.chess;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Position {
    private int charPosition;
    private int numericPosition;

    public Position(int charPosition, int numericPosition) {
        setCharPosition(charPosition);
        setNumericPosition(numericPosition);
    }

    public Position(@NotNull Position that) {
        setCharPosition(that.getCharPosition());
        setNumericPosition(that.getNumericPosition());
    }

    public int getCharPosition() {
        return charPosition;
    }

    public void setCharPosition(int charPosition) {
        // FIXME hardcoded 8
        // TODO add check: charPosition must be greater than 0
        Objects.checkIndex(charPosition, 8);
        this.charPosition = charPosition;
    }

    public int getNumericPosition() {
        return numericPosition;
    }

    public void setNumericPosition(int numericPosition) {
        // FIXME hardcoded 8
        // TODO add check: numeric must be greater than 0
        Objects.checkIndex(numericPosition, 8);
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

    /* TODO add getters
     *  getNumericIndex
     *  getCharIndex
     */
}
