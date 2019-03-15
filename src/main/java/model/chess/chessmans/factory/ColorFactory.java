package model.chess.chessmans.factory;

import model.chess.chessmans.*;

public class ColorFactory implements ChessmanFactory {
    private final ChessmanColor color;

    public ColorFactory(ChessmanColor color) {
        this.color = color;
    }

    @Override
    public Chessman createPawn() {
        return new Pawn(color);
    }

    @Override
    public Chessman createBishop() {
        return new Bishop(color);
    }

    @Override
    public Chessman createKing() {
        return new King(color);
    }

    @Override
    public Chessman createQueen() {
        return new Queen(color);
    }

    @Override
    public Chessman createKnight() {
        return new Knight(color);
    }

    @Override
    public Chessman createRook() {
        return new Rook(color);
    }
}
