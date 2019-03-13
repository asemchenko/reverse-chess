package model.chess.chessmans;

import model.chess.ChessmanColor;
import model.chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO подумай как избежать дублирования кода тестовых методов для разных фигур
class RookTest {
    // ======================================== TEST PARAMETERS =========================================
    private static final Position startPosition = new Position('g', 7);
    private static final List<Position> possibleMoves = Arrays.asList(
            // horizontal
            new Position('a', 7),
            new Position('b', 7),
            new Position('c', 7),
            new Position('d', 7),
            new Position('e', 7),
            new Position('f', 7),
            new Position('h', 7),
            // vertical
            new Position('g', 1),
            new Position('g', 2),
            new Position('g', 3),
            new Position('g', 4),
            new Position('g', 5),
            new Position('g', 6),
            new Position('g', 8)
    );
    private static final Position dstPosition = new Position('c', 7);
    private static final List<Position> dstRoute = Arrays.asList(
            new Position('f', 7),
            new Position('e', 7),
            new Position('d', 7)
    );
    private static final Position unreachablePosition = new Position('e', 5);
    // ==================================================================================================
    private Rook chessman;

    @BeforeEach
    void setUp() {
        chessman = new Rook(ChessmanColor.WHITE, startPosition);
    }

    @Test
    void testIsReachableTrue() {
        // check moves
        for (var p : possibleMoves) {
            assertTrue(chessman.isReachable(p));
        }
    }

    @Test
    void testIsReachableFalse() {
        var nonPossible = new LinkedList<>(Position.getAllPossiblePositions());
        nonPossible.removeAll(possibleMoves);
        for (var p : nonPossible) {
            assertFalse(chessman.isReachable(p));
        }
    }

    @Test
    void testRouteWhenExists() {
        final List<Position> actualRoute = new ArrayList<>();
        chessman.getRouteTo(dstPosition).forEach(actualRoute::add);
        assertEquals(dstRoute, actualRoute);
    }

    @Test
    void testRouteWhenDoesNotExist() {
        // unreachable from `startPosition`
        assertNull(chessman.getRouteTo(unreachablePosition));
    }
}