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
class KingTest extends ChessmanTest {
    @BeforeAll
    void init() {
        final ChessmanColor defaultColor = ChessmanColor.WHITE;
        setConstructor((startPosition) -> new King(defaultColor, startPosition));
    }

    Stream<Arguments> startPositionAndReachableCellsProvider() {
        return Stream.of(
                arguments(
                        new Position('a', 1),
                        Arrays.asList(
                                // one diagonal
                                new Position('a', 2),
                                new Position('b', 1),
                                new Position('b', 2)
                        )
                ),
                arguments(
                        new Position('e', 6),
                        Arrays.asList(
                                // one diagonal
                                new Position('d', 7),
                                new Position('e', 7),
                                new Position('f', 7),

                                new Position('d', 5),
                                new Position('e', 5),
                                new Position('f', 5),

                                new Position('d', 6),
                                new Position('f', 6)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("startPositionAndReachableCellsProvider")
    @Override
    void testCanReachTrue(Position startPosition, List<Position> reachableCells) {
        super.testCanReachTrue(startPosition, reachableCells);
    }

    @ParameterizedTest
    @MethodSource("startPositionAndReachableCellsProvider")
    @Override
    void testCanReachFalse(Position startPosition, List<Position> reachableCells) {
        super.testCanReachFalse(startPosition, reachableCells);
    }


    Stream<Arguments> startPositionAndDstPositionAndExpectedRoute() {
        return Stream.of(
                arguments(
                        new Position('a', 8),
                        new Position('b', 7),
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
                        new Position('c', 7),
                        new Position('a', 7)
                ),
                arguments(
                        new Position('f', 6),
                        new Position('g', 1)
                ),
                arguments(
                        new Position('d', 2),
                        new Position('g', 5)
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