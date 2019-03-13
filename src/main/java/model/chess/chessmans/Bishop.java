package model.chess.chessmans;

import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// TODO проверить все фигуры на возможность походить не изменяя своего положения
public class Bishop extends Chessman {
    public Bishop(ChessmanColor color, Position position) {
        super(color, position);
    }

    @Override
    protected Iterator<Position> route(Position dst) {
        return new Iterator<Position>() {
            private Position p = new Position(position);
            private int charD = Integer.signum(dst.charSubstract(position));
            private int numD = Integer.signum(dst.numericSubstract(position));

            @Override
            public boolean hasNext() {
                // FIXME как то не оч создавать новый объект position постоянно
                var nPos = new Position(p);
                nPos.move(charD, numD);
                return !nPos.equals(dst);
            }

            @Override
            public Position next() {
                p.move(charD, numD);
                return new Position(p);
            }
        };
    }

    /**
     * Checks if <code>dst</code> is on the same diagonal as
     * this chessman
     *
     * @param dst destination of the move
     * @return the result of check
     */
    protected boolean isReachable(@NotNull Position dst) {
        int numD = position.numericDistance(dst);
        int charD = position.charDistance(dst);
        return (numD == charD) && (numD != 0);
    }
}
