package model.chess.chessmans;

import model.chess.ChessmanColor;
import model.chess.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    private static final Position startPosition = new Position('d', 4);
    private static final Position allyPosition = new Position('c', 3);
    private static final List<Position> possibleMoves = Arrays.asList(
            // one diagonal
            new Position('a', 1),
            new Position('b', 2),
            new Position('c', 3),
            new Position('e', 5),
            new Position('f', 6),
            new Position('g', 7),
            new Position('h', 8),
            // other diagonal
            new Position('a', 7),
            new Position('b', 6),
            new Position('c', 5),
            new Position('e', 3),
            new Position('f', 2),
            new Position('g', 1)
    );
    private Chessman bishop;

    @Test
    void testIsReachableTrue() {
        bishop = new Bishop(ChessmanColor.WHITE, startPosition);
        // check moves
        for (var p : possibleMoves) {
            assertTrue(bishop.isReachable(p));
        }
    }

    @Test
    void testIsReachableFalse() {
        bishop = new Bishop(ChessmanColor.WHITE, startPosition);
        var nonPossible = new LinkedList<>(Position.getAllPossiblePositions());
        nonPossible.removeAll(possibleMoves);
        for (var p : nonPossible) {
            assertFalse(bishop.isReachable(p));
        }
    }

    @Test
    void testRouteWhenExists() {
        final Position dstPosition = new Position('a', 7);
        /// right route from startPosition(d4) to dstPosition(a7)
        final List<Position> rightRoute = Arrays.asList(
                new Position('c', 5),
                new Position('b', 6)
        );
        bishop = new Bishop(ChessmanColor.WHITE, startPosition);
        final List<Position> actualRoute = new ArrayList<>();
        bishop.getRouteTo(dstPosition).forEach(actualRoute::add);
        assertEquals(rightRoute, actualRoute);
    }

    @Test
    void testRouteWhenDoesNotExist() {
        bishop = new Bishop(ChessmanColor.WHITE, startPosition);
        // unreachable from `startPosition`
        final Position dstPosition = new Position('g', 6);
        assertNull(bishop.getRouteTo(dstPosition));
    }
}