package model.chess.chessmans;

import model.chess.ChessmanColor;
import model.chess.Position;
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
class RookTest extends ChessmanTest {
    @BeforeAll
    void init() {
        final ChessmanColor defaultColor = ChessmanColor.BLACK;
        setConstructor((startPosition) -> new Rook(defaultColor, startPosition));
    }

    Stream<Arguments> startPositionAndReachableCellsProvider() {
        return Stream.of(
                arguments(
                        new Position('g', 7),
                        Arrays.asList(
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
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("startPositionAndReachableCellsProvider")
    @Override
    void testIsReachableTrue(Position startPosition, List<Position> reachableCells) {
        super.testIsReachableTrue(startPosition, reachableCells);
    }

    @ParameterizedTest
    @MethodSource("startPositionAndReachableCellsProvider")
    @Override
    void testIsReachableFalse(Position startPosition, List<Position> reachableCells) {
        super.testIsReachableFalse(startPosition, reachableCells);
    }


    Stream<Arguments> startPositionAndDstPositionAndExpectedRoute() {
        return Stream.of(
                arguments(
                        new Position('g', 7),
                        new Position('c', 7),
                        Arrays.asList(
                                new Position('f', 7),
                                new Position('e', 7),
                                new Position('d', 7)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("startPositionAndDstPositionAndExpectedRoute")
    @Override
    void testRouteWhenExists(Position startPosition, Position dstPosition, List<Position> expectedRoute) {
        super.testRouteWhenExists(startPosition, dstPosition, expectedRoute);
    }

    Stream<Arguments> startPositionAndUnreachableDstPosition() {
        return Stream.of(
                arguments(
                        new Position('g', 7),
                        new Position('e', 5)
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