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
class BishopTest extends ChessmanTest {
    @BeforeAll
    void init() {
        final ChessmanColor defaultColor = ChessmanColor.BLACK;
        setConstructor((startPosition) -> new Bishop(defaultColor, startPosition));
    }

    Stream<Arguments> startPositionAndReachableCellsProvider() {
        return Stream.of(
                arguments(
                        new Position('d', 4),
                        Arrays.asList(
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
                        new Position('d', 4),
                        new Position('a', 7),
                        Arrays.asList(
                                new Position('c', 5),
                                new Position('b', 6)
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
                        new Position('d', 4),
                        new Position('g', 6)
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