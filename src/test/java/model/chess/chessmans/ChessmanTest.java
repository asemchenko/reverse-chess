package model.chess.chessmans;

import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Container for common testing algorithms
 * of each chessman
 */
public abstract class ChessmanTest {
    private Function<Position, Chessman> constructor;

    public void setConstructor(Function<Position, Chessman> constructor) {
        this.constructor = constructor;
    }

    void testRouteWhenDoesNotExist(final Position startPosition, final Position unreachablePosition) {
        Chessman chessman = constructor.apply(startPosition);
        // unreachable from `startPosition`
        assertThrows(ChessException.class, () -> chessman.getRouteSimpleMove(unreachablePosition));
    }

    void testRouteWhenExists(final Position startPosition, final Position dstPosition, final List<Position> expectedRoute) throws ChessException {
        Chessman chessman = constructor.apply(startPosition);
        final List<Position> actualRoute = new ArrayList<>();
        chessman.getRouteSimpleMove(dstPosition).forEach(actualRoute::add);
        assertEquals(expectedRoute, actualRoute);
    }

    void testCanReachFalse(final Position startPosition, List<Position> reachableCells) {
        Chessman chessman = constructor.apply(startPosition);
        var nonPossible = new LinkedList<>(Position.getAllPossiblePositions());
        nonPossible.removeAll(reachableCells);
        for (var p : nonPossible) {
            assertFalse(chessman.canReach(p), String.format("%s should not be reachable from %s", p, startPosition));
        }
    }

    void testCanReachTrue(final Position startPosition, List<Position> reachableCells) {
        Chessman chessman = constructor.apply(startPosition);
        // check moves
        for (var p : reachableCells) {
            assertTrue(chessman.canReach(p), String.format("%s should be reachable from %s", p, startPosition));
        }
    }
}
