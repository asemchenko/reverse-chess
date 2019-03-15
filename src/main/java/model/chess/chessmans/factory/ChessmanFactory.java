package model.chess.chessmans.factory;

import model.chess.chessmans.Chessman;
import model.chess.chessmans.ChessmanColor;

public interface ChessmanFactory {

    Chessman createPawn();

    Chessman createBishop();

    Chessman createKing();

    Chessman createQueen();

    Chessman createKnight();

    Chessman createRook();
}
