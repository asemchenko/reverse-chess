package model.chess.chessmans;

import model.chess.Position;

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
        assertNull(chessman.getRouteTo(unreachablePosition));
    }

    void testRouteWhenExists(final Position startPosition, final Position dstPosition, final List<Position> expectedRoute) {
        Chessman chessman = constructor.apply(startPosition);
        final List<Position> actualRoute = new ArrayList<>();
        chessman.getRouteTo(dstPosition).forEach(actualRoute::add);
        assertEquals(expectedRoute, actualRoute);
    }

    void testIsReachableFalse(final Position startPosition, List<Position> reachableCells) {
        Chessman chessman = constructor.apply(startPosition);
        var nonPossible = new LinkedList<>(Position.getAllPossiblePositions());
        nonPossible.removeAll(reachableCells);
        for (var p : nonPossible) {
            assertFalse(chessman.isReachable(p));
        }
    }

    void testIsReachableTrue(final Position startPosition, List<Position> reachableCells) {
        Chessman chessman = constructor.apply(startPosition);
        // check moves
        for (var p : reachableCells) {
            assertTrue(chessman.isReachable(p));
        }
    }
}
