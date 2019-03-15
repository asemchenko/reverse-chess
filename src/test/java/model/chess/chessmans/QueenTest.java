package model.chess.chessmans;

import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QueenTest extends ChessmanTest {
    @BeforeAll
    void init() {
        final ChessmanColor defaultColor = ChessmanColor.BLACK;
        setConstructor((startPosition) -> new Queen(defaultColor, startPosition));
    }

    Stream<Arguments> startPositionAndReachableCellsProvider() {
        return Stream.of(
                arguments(
                        new Position('c', 4),
                        Arrays.asList(
                                // first diagonal
                                new Position('a', 6),
                                new Position('b', 5),
                                new Position('d', 3),
                                new Position('e', 2),
                                new Position('f', 1),
                                // second diagonal
                                new Position('a', 2),
                                new Position('b', 3),
                                new Position('d', 5),
                                new Position('e', 6),
                                new Position('f', 7),
                                new Position('g', 8),
                                // vertical
                                new Position('c', 8),
                                new Position('c', 7),
                                new Position('c', 6),
                                new Position('c', 5),
                                new Position('c', 3),
                                new Position('c', 2),
                                new Position('c', 1),
                                // horizontal
                                new Position('a', 4),
                                new Position('b', 4),
                                new Position('d', 4),
                                new Position('e', 4),
                                new Position('f', 4),
                                new Position('g', 4),
                                new Position('h', 4)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("startPositionAndReachableCellsProvider")
    @Override
    void testCanBeMovedTrue(Position startPosition, List<Position> reachableCells) {
        super.testCanBeMovedTrue(startPosition, reachableCells);
    }

    @ParameterizedTest
    @MethodSource("startPositionAndReachableCellsProvider")
    @Override
    void testCanBeMovedFalse(Position startPosition, List<Position> reachableCells) {
        super.testCanBeMovedFalse(startPosition, reachableCells);
    }


    Stream<Arguments> startPositionAndDstPositionAndExpectedRoute() {
        return Stream.of(
                arguments(
                        new Position('g', 1),
                        new Position('d', 4),
                        Arrays.asList(
                                new Position('f', 2),
                                new Position('e', 3)
                        )
                ),
                arguments(
                        new Position('b', 6),
                        new Position('f', 6),
                        Arrays.asList(
                                new Position('c', 6),
                                new Position('d', 6),
                                new Position('e', 6)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("startPositionAndDstPositionAndExpectedRoute")
    @Override
    void testRouteWhenExists(Position startPosition, Position dstPosition, List<Position> expectedRoute) throws ChessException {
        super.testRouteWhenExists(startPosition, dstPosition, expectedRoute);
    }

    Stream<Arguments> startPositionAndUnreachableDstPosition() {
        return Stream.of(
                arguments(
                        new Position('f', 4),
                        new Position('g', 6)
                ),
                arguments(
                        new Position('c', 7),
                        new Position('a', 8)
                ),
                arguments(
                        new Position('f', 5),
                        new Position('e', 1)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("startPositionAndUnreachableDstPosition")
    @Override
    void testRouteWhenDoesNotExist(Position startPosition, Position unreachablePosition) {
        super.testRouteWhenDoesNotExist(startPosition, unreachablePosition);
    }
}