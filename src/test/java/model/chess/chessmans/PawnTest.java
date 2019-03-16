package model.chess.chessmans;

import model.chess.chessboard.Position;
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
class PawnTest extends ChessmanTest {

    protected void setConstructorWithColor(ChessmanColor color) {
        setConstructor((s) -> new Pawn(color, s));
    }

    Stream<Arguments> colorAndStartPositionAndReachableCellsProvider() {
        return Stream.of(
                // WHITE INITIAL MOVE
                arguments(
                        ChessmanColor.WHITE,
                        new Position('e', 2),
                        Arrays.asList(
                                new Position('e', 3),
                                new Position('e', 4)
                        )
                ),
                // BLACK INITIAL MOVE
                arguments(
                        ChessmanColor.BLACK,
                        new Position('c', 7),
                        Arrays.asList(
                                new Position('c', 6),
                                new Position('c', 5)
                        )
                ),
                // WHITE USUAL MOVE
                arguments(
                        ChessmanColor.WHITE,
                        new Position('c', 4),
                        Arrays.asList(
                                new Position('c', 5)
                        )
                ),
                // BLACK USUAL MOVE
                arguments(
                        ChessmanColor.BLACK,
                        new Position('g', 5),
                        Arrays.asList(
                                new Position('g', 4)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("colorAndStartPositionAndReachableCellsProvider")
    void testCanReachTrue(ChessmanColor color, Position startPosition, List<Position> reachableCells) {
        setConstructorWithColor(color);
        super.testCanReachTrue(startPosition, reachableCells);
    }

    @ParameterizedTest
    @MethodSource("colorAndStartPositionAndReachableCellsProvider")
    void testCanReachFalse(ChessmanColor color, Position startPosition, List<Position> reachableCells) {
        setConstructorWithColor(color);
        super.testCanReachFalse(startPosition, reachableCells);
    }


    Stream<Arguments> colorAndStartPositionAndDstPositionAndExpectedRoute() {
        return Stream.of(
                // WHITE INITIAL MOVE
                arguments(
                        ChessmanColor.WHITE,
                        new Position('h', 2),
                        new Position('h', 4),
                        Arrays.asList(
                                new Position('h', 3)
                        )
                ),
                // BLACK INITIAL MOVE
                arguments(
                        ChessmanColor.BLACK,
                        new Position('f', 7),
                        new Position('f', 5),
                        Arrays.asList(
                                new Position('f', 6)
                        )
                ),
                // WHITE USUAL MOVE
                arguments(
                        ChessmanColor.WHITE,
                        new Position('d', 2),
                        new Position('d', 3),
                        Collections.emptyList()
                ),
                // BLACK USUAL MOVE
                arguments(
                        ChessmanColor.BLACK,
                        new Position('e', 7),
                        new Position('e', 6),
                        Collections.emptyList()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("colorAndStartPositionAndDstPositionAndExpectedRoute")
    void testRouteWhenExists(ChessmanColor color, Position startPosition, Position dstPosition, List<Position> expectedRoute) {
        setConstructorWithColor(color);
        try {
            super.testRouteWhenExists(startPosition, dstPosition, expectedRoute);
        } catch (model.chess.exceptions.ChessException e) {
            e.printStackTrace();
        }
    }

    Stream<Arguments> colorAndStartPositionAndUnreachableDstPosition() {
        return Stream.of(
                // WHITE BACK MOVE
                arguments(
                        ChessmanColor.WHITE,
                        new Position('d', 5),
                        new Position('d', 4)
                ),
                // BLACK BACK MOVE
                arguments(
                        ChessmanColor.BLACK,
                        new Position('a', 6),
                        new Position('a', 7)
                ),
                arguments(
                        ChessmanColor.BLACK,
                        new Position('c', 5),
                        new Position('c', 3)
                ),
                arguments(
                        ChessmanColor.WHITE,
                        new Position('g', 3),
                        new Position('g', 5)
                ),
                arguments(
                        ChessmanColor.BLACK,
                        new Position('e', 6),
                        new Position('d', 5)
                ),
                arguments(
                        ChessmanColor.WHITE,
                        new Position('h', 6),
                        new Position('g', 7)
                ),
                arguments(
                        ChessmanColor.WHITE,
                        new Position('e', 2),
                        new Position('f', 4)
                ),
                arguments(
                        ChessmanColor.WHITE,
                        new Position('e', 2),
                        new Position('f', 3)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("colorAndStartPositionAndUnreachableDstPosition")
    void testRouteWhenDoesNotExist(ChessmanColor color, Position startPosition, Position unreachablePosition) {
        setConstructorWithColor(color);
        super.testRouteWhenDoesNotExist(startPosition, unreachablePosition);
    }
}