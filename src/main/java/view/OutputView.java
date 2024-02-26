package view;

import domain.Connection;
import domain.Game;
import domain.Line;
import domain.Lines;
import domain.Members;
import java.util.List;

public class OutputView {

    private static final int MAX_NAME_LENGTH = 5;
    private static final String CONNECTED_CHARACTER = "-";
    private static final String DISCONNECTED_CHARACTER = " ";
    private static final String FRAME_OF_LADDER = "|";

    public void printResult(Game game) {
        System.out.println("실행결과");
        System.out.println(resolveMembers(game.getMembers()));
        System.out.println(resolveLines(game.getLines()));
    }

    private String resolveMembers(Members members) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : members.getNames()) {
            stringBuilder.append(String.format("%" + MAX_NAME_LENGTH + "s ", name));
        }
        return stringBuilder.toString();
    }

    private String resolveLines(Lines ladder) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Line> lines = ladder.getLines();
        for (Line line : lines) {
            stringBuilder.append(resolveLine(line));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String resolveLine(Line line) {
        List<Connection> connections = line.getConnections();
        StringBuilder stringBuilder = new StringBuilder(repeatCharacter(DISCONNECTED_CHARACTER, MAX_NAME_LENGTH - 1));
        for (Connection connection : connections) {
            stringBuilder.append(FRAME_OF_LADDER);
            stringBuilder.append(resolveConnection(connection));
        }
        stringBuilder.append(FRAME_OF_LADDER);
        return stringBuilder.toString();
    }

    private String resolveConnection(Connection connection) {
        if (connection.equals(Connection.CONNECTED)) {
            return repeatCharacter(CONNECTED_CHARACTER, MAX_NAME_LENGTH);
        }
        return repeatCharacter(DISCONNECTED_CHARACTER, MAX_NAME_LENGTH);
    }

    private String repeatCharacter(String character, int times) {
        return character.repeat(times);
    }
}
