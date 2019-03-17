package model.chess.chessmans;

import model.chess.chessboard.Position;

import java.util.ArrayList;

public class King extends Chessman {
    public King(ChessmanColor color, Position position) {
        super(color, position);
    }

    public King(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        return position.charDistance(dst) <= 1
                && position.numericDistance(dst) <= 1;
    }

    @Override
    public Iterable<Position> getUnderAttack() {
        ArrayList<Position> p = new ArrayList<>();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (position.canBeMoved(i,j)) {
                    p.add(position.move(i,j));
                }
            }
        }
        return p;
    }

    @Override
    protected char getCharRepresentationIgnoreColor() {
        return 'k';
    }
}
