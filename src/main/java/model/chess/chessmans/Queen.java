package model.chess.chessmans;

import model.chess.chessboard.Position;

public class Queen extends Chessman {
    public Queen(ChessmanColor color, Position position) {
        super(color, position);
    }

    public Queen(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        // TODO вроде ж норм варик?
        return new Rook(getColor(), getPosition()).isReachable(dst)
                || new Bishop(getColor(), getPosition()).isReachable(dst);
    }
}
