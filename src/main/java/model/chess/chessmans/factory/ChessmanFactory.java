package model.chess.chessmans.factory;

import model.chess.chessmans.Chessman;

public interface ChessmanFactory {

    Chessman createPawn();

    Chessman createBishop();

    Chessman createKing();

    Chessman createQueen();

    Chessman createKnight();

    Chessman createRook();
}
