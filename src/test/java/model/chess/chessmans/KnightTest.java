package model.chess.chessmans;

import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KnightTest extends ChessmanTest {
    @BeforeAll
    void init() {
        final ChessmanColor defaultColor = ChessmanColor.BLACK;
        setConstructor((startPosition) -> new Knight(defaultColor, startPosition));
    }

    Stream<Arguments> startPositionAndReachableCellsProvider() {
        return Stream.of(
                arguments(
                        new Position('c', 3),
                        Arrays.asList(
                                new Position('b', 1),
                                new Position('d', 1),
                                new Position('b', 5),
                                new Position('d', 5),

                                new Position('a', 4),
                                new Position('e', 4),
                                new Position('a', 2),
                                new Position('e', 2)
                        )
                ),
                arguments(
                        new Position('b', 8),
                        Arrays.asList(
                                new Position('a', 6),
                                new Position('c', 6),
                                new Position('d', 7)
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
                        new Position('f', 1),
                        new Position('g', 3),
                        Collections.emptyList()
                ),
                arguments(
                        new Position('e', 4),
                        new Position('f', 6),
                        Collections.emptyList()
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
                        new Position('d', 4),
                        new Position('c', 5)
                ),
                arguments(
                        new Position('b', 2),
                        new Position('b', 3)
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