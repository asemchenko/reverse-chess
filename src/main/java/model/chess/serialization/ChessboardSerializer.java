package model.chess.serialization;

import model.chess.chessboard.Chessboard;

import java.util.Objects;

public class ChessboardSerializer {
    private Chessboard board;

    public ChessboardSerializer(Chessboard board) {
        this.board = board;
    }

    public SerializedBoard serialize() {
        SerializedBoard serializedBoard = new SerializedBoard();
        for (var l : board.asList()) {
            StringBuilder builder = new StringBuilder();
            for (var c : l) {
                if (Objects.isNull(c)) {
                    builder.append("·");
                } else {
                    builder.append(c.toChar());
                }
                builder.append(' ');
            }
            serializedBoard.addLine(builder.toString());
        }
        return serializedBoard;
    }
}
