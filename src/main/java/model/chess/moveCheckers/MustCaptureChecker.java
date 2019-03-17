package model.chess.moveCheckers;

import com.google.common.collect.Iterables;
import model.chess.Move;
import model.chess.chessboard.Chessboard;
import model.chess.chessboard.Position;
import model.chess.chessmans.Chessman;
import model.chess.chessmans.ChessmanColor;
import model.chess.chessmans.Knight;
import model.chess.chessmans.Queen;
import model.chess.exceptions.ChessException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// TODO код этого класса требует хорошего рефакторинга

/**
 * Checks if the move follows a rule
 * 'Gamer have to capture enemy if it possible'
 */
public class MustCaptureChecker extends MoveChecker {
    private Chessboard board;
    private Map<Position, Set<Position>> possibleAttacks = new HashMap<>();
    //    private Map<Position, Set<Position>> blackPossibleAttacks = new HashMap<>();
    private RouteChecker routeChecker;

    public MustCaptureChecker(Chessboard board) {
        this.board = board;
        routeChecker = new RouteChecker(board);
    }

    @Override
    protected void validate(Move move) throws ChessException {
        // TODO проверка не должен ли бить
        var srcChessmanColor = board.get(move.getSrcPosition()).getColor();
        boolean isMoveAnAttack = board.get(move.getDstPosition()) != null;
        if (!getPossibleAttacks(srcChessmanColor).isEmpty() && !isMoveAnAttack) {
            throw new ChessException("You must capture");
        } else if (isMoveAnAttack && !getPossibleAttacks(srcChessmanColor).contains(move)) { // TODO удали, else if тут только в целях отладки
            throw new Error("An error occurred. It is obviously a programmers guilt");
        }
        processMove(move);
        printPossibleAttacks();
    }

    private void processMove(Move move) {
//        Position srcPosition = move.getSrcPosition();
//        Chessman srcChessman = board.remove(srcPosition);
//        var attackers = getAttackersOfCell(srcPosition);
//        attackers.forEach(attacker -> updatePossibleAttacksForCell(attacker.getPosition()));
//        board.put(move.getDstPosition(), srcChessman);
//        updatePossibleAttacksForCell(move.getDstPosition());
//        board.remove(move.getDstPosition());
//        board.put(move.getSrcPosition(), srcChessman);

        Position srcPosition = move.getSrcPosition();
        Chessman srcChessman = board.remove(srcPosition);
        updatePossibleAttacksForCell(srcPosition);
        var attackers = getAttackersOfCell(srcPosition);
        board.put(move.getDstPosition(), srcChessman);
        attackers.forEach(attacker -> updatePossibleAttacksForCell(attacker.getPosition()));
        var newAttackers = getAttackersOfCell(move.getDstPosition());
        newAttackers.forEach(attacker -> updatePossibleAttacksForCell(attacker.getPosition()));
        updatePossibleAttacksForCell(move.getDstPosition());
    }

    /**
     * Find every chessman that can attack <code>cell</code>
     *
     * @param cell cell position
     * @return attackers
     */
    private Iterable<Chessman> getAttackersOfCell(Position cell) {
        Iterable<Position> positions = Iterables.concat(
                new Queen(null, cell).getUnderAttack(),
                new Knight(null, cell).getUnderAttack()
        );
        return filterAttackers(positions, cell).map(board::get).collect(Collectors.toList());
    }

    private Stream<Position> filterAttackers(Iterable<Position> potentialAttackers, Position cell) {
        // TODO оптимизируй это
        var p = StreamSupport.stream(potentialAttackers.spliterator(), false);
        return p.filter(position -> Objects.nonNull(board.get(position)))
                .filter(position -> routeChecker.validateNoThrow(new Move(position, cell)))
                .collect(Collectors.toSet()) // removing repeatable elements
                .stream();
    }

    private void updatePossibleAttacksForCell(Position cellPosition) {
        Chessman cellChessman = board.get(cellPosition);
        if (Objects.isNull(cellChessman)) {
            possibleAttacks.remove(cellPosition);
        } else {
            var possibleAttackPosition = cellChessman.getUnderAttack();
            // removing positions that are empty
            var p = StreamSupport.stream(possibleAttackPosition.spliterator(), false);
            var attacks = p.filter(position -> Objects.nonNull(board.get(position)))
                    .filter((position -> cellChessman.getColor().getOpposite().equals(board.get(position).getColor())))
                    .filter(position -> routeChecker.validateNoThrow(new Move(cellPosition, position)))
                    .collect(Collectors.toSet());
            // updating
            if (!attacks.isEmpty()) {
                possibleAttacks.put(cellPosition, attacks);
            } else {
                possibleAttacks.remove(cellPosition);
            }
        }
    }

    private List<Move> getPossibleAttacks(ChessmanColor color) {
        List<Move> r = new ArrayList<>();
        for (var entry : possibleAttacks.entrySet()) {
            var srcPos = entry.getKey();
            if (board.get(srcPos).getColor().equals(color)) {
                for (var dstPos : entry.getValue()) {
                    r.add(new Move(srcPos, dstPos));
                }
            }
        }
        return r;
    }

    private void printPossibleAttacks() {
        System.out.println("\n=================== POSSIBLE WHITE ATTACKS ===================");
        getPossibleAttacks(ChessmanColor.WHITE).forEach(System.out::println);
        System.out.println("=================== POSSIBLE BLACK ATTACKS ===================\n");
        getPossibleAttacks(ChessmanColor.BLACK).forEach(System.out::println);
    }
}