package model.chess.chessmans;

import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public abstract class Chessman {
    protected ChessmanColor color;
    protected Position position;

    public Chessman(ChessmanColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Chessman(ChessmanColor color) {
        this.color = color;
    }

    public final Position getPosition() {
        return position;
    }

    public final void setPosition(Position position) {
        this.position = position;
    }

    public final ChessmanColor getColor() {
        return color;
    }

    /**
     * Returns range of positions that chessman visits during walking
     * from it's current position to <code>dst</code>
     *
     * @param dst destination
     * @return range of intermediate positions
     * @throws ChessException if <code>dst</code> can not be reached
     */
    @NotNull
    public final Iterable<Position> getRouteSimpleMove(Position dst) throws ChessException {
        if (canReach(dst)) {
            return () -> route(dst);
        }
        throw new ChessException(position + " can not be moved to " + dst);
    }

    /**
     * Returns range of positions that chessman visits during walking
     * from it's current position to <code>dst</code>
     *
     * @param dst destination
     * @return range of intermediate positions
     * @throws ChessException if <code>dst</code> can not be captured
     */
    @NotNull
    public final Iterable<Position> getRouteCaptureMove(Position dst) throws ChessException {
        if (canCapture(dst)) {
            return () -> captureRoute(dst);
        }
        throw new ChessException(position + " can not be moved to " + dst);
    }

    public final boolean canReach(Position dst) {
        if (position.equals(dst)) return false;
        return isReachable(dst);
    }

    protected abstract boolean isReachable(Position dst);

    /* Most of all figures capture in a such way as simple move
     * Should be override if needed
     */
    public boolean canCapture(Position dst) {
        return canReach(dst);
    }

    /* Most common behaviour. Should be override if needed */
    protected Iterator<Position> route(Position dst) {
        int charDirection = Integer.signum(dst.charDiff(position));
        int numDirection = Integer.signum(dst.numericDiff(position));
        return new RouteIterator(position, dst, charDirection, numDirection);
    }

    /* Most common behaviour. Should be override if needed */
    protected Iterator<Position> captureRoute(Position dst) {
        return route(dst);
    }

    public final char toChar() {
        char c = getCharRepresentationIgnoreColor();
        if (color.equals(ChessmanColor.WHITE)) {
            return Character.toUpperCase(c);
        }
        return Character.toLowerCase(c);
    }

    protected abstract char getCharRepresentationIgnoreColor();

    protected class RouteIterator implements Iterator<Position> {
        private Position src;
        private Position dst;
        private int charD;
        private int numD;

        public RouteIterator(Position src, Position dst, int charD, int numD) {
            this.src = new Position(src);
            this.dst = dst;
            this.charD = charD;
            this.numD = numD;
        }

        @Override
        public boolean hasNext() {
            var nPos = new Position(src);
            if (nPos.canBeMoved(charD, numD)) {
                nPos = nPos.move(charD, numD);
            } else {
                return false;
            }
            return !nPos.equals(dst);
        }

        @Override
        public Position next() {
            src = src.move(charD, numD);
            return new Position(src);
        }
    }

}
