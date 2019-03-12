package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    private Chessboard.Chessman bishop;

    @Test
    public void testCanBeMoved() {
        // preparing
        Chessboard chessboard = mock(Chessboard.class);
        // there is only one chessman on the board
        bishop = new Bishop(chessboard, ChessmanColor.WHITE, startPosition);
        when(chessboard.get(any())).thenAnswer(new Answer<Chessboard.Chessman>() {
            @Override
            public Chessboard.Chessman answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object p = invocationOnMock.getArgument(0);
                return startPosition.equals(p) ? bishop : null;
            }
        });
        // check moves
        for (Position p : Position.getAllPossiblePositions()) {
            if (possibleMoves.contains(p)) {
                assertTrue(bishop.canBeMoved(p));
            } else {
                assertFalse(bishop.canBeMoved(p), () -> String.format("%s is not a valid move from %s", p, startPosition));
            }
        }
    }

    @Test
    public void testForbidCaptureAllyFigure() {
        // preparing
        Chessboard chessboard = mock(Chessboard.class);
        // there is only one chessman on the board
        bishop = new Bishop(chessboard, ChessmanColor.WHITE, startPosition);
        Chessboard.Chessman ally = mock(Chessboard.Chessman.class);
        when(ally.getColor()).thenReturn(ChessmanColor.WHITE);
        when(chessboard.get(any())).thenAnswer(new Answer<Chessboard.Chessman>() {
            @Override
            public Chessboard.Chessman answer(InvocationOnMock invocationOnMock) throws Throwable {
                Position p = invocationOnMock.getArgument(0);
                if (p.equals(startPosition)) {
                    return bishop;
                } else if (p.equals(allyPosition)) {
                    return ally;
                }
                return null;
            }
        });
        assertFalse(bishop.canBeMoved(allyPosition), "Chessman can't capture the same color chessman");
    }
}