package bg.sofia.uni.fmi.mjt.football;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public record Player(String name, String fullName, LocalDate birthDate,
                     int age, double heightCm, double weightKg,
                     List<Position> positions, String nationality, int overallRating,
                     int potential, long valueEuro, long wageEuro, Foot preferredFoot) {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

    private static final int NAME = 0;
    private static final int FULL_NAME = 1;
    private static final int BIRTH = 2;
    private static final int AGE = 3;
    private static final int HEIGHT = 4;
    private static final int WEIGHT = 5;
    private static final int POSITIONS = 6;
    private static final int NATION = 7;
    private static final int RATING = 8;
    private static final int POTENTIAL = 9;
    private static final int VALUE = 10;
    private static final int WAGE = 11;
    private static final int FOOT = 12;

    public static Player of(String line) {
        if (line == null) {
            throw new IllegalArgumentException();
        }
        String[] tokens = line.split(";");

        String name = tokens[NAME];
        String fullname = tokens[FULL_NAME];
        LocalDate releaseDate = LocalDate.parse(tokens[BIRTH], DATE_FORMATTER);
        int age = Integer.parseInt(tokens[AGE]);
        double height = Double.parseDouble(tokens[HEIGHT]);
        double weight = Double.parseDouble(tokens[WEIGHT]);
        String[] buffer = tokens[POSITIONS].split(",");
        List<Position> positions = new LinkedList<>();
        for (String position: buffer) {
            positions.add(Position.valueOf(position));
        }
        String nationality = tokens[NATION];
        int rating = Integer.parseInt(tokens[RATING]);
        int potential = Integer.parseInt(tokens[POTENTIAL]);
        long value = Long.valueOf(tokens[VALUE]);
        long wage = Long.valueOf(tokens[WAGE]);
        Foot foot = Foot.valueOf(tokens[FOOT].toUpperCase());

        return new Player(name, fullname, releaseDate, age, height, weight,
                positions, nationality, rating, potential, value, wage, foot);
    }
}
