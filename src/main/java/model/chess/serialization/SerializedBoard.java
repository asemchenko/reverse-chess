package model.chess.serialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerializedBoard {
    private final List<String> lines = new ArrayList<>(8);

    void addLine(String line) {
        lines.add(line);
    }

    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
