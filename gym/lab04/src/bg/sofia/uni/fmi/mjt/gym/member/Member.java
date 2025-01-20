package bg.sofia.uni.fmi.mjt.gym.member;

import bg.sofia.uni.fmi.mjt.gym.workout.Exercise;
import bg.sofia.uni.fmi.mjt.gym.workout.Workout;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.SequencedCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Member implements GymMember {

    String personalIdNumber;
    Address address;
    String name;
    Integer age;
    Gender gender;
    public Map<DayOfWeek, Workout> program;

    public Member(Address address, String name, int age, String personalIdNumber, Gender gender) {
        this.address = address;
        this.name = name;
        this.age = age;
        this.personalIdNumber = personalIdNumber;
        this.gender = gender;
        program = new HashMap<>();
    }

    @Override
    public int hashCode() {
        return personalIdNumber.hashCode();
    }

    @Override
    public boolean equals(Object o1) {
        return this.hashCode() == o1.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public Map<DayOfWeek, Workout> getTrainingProgram() {
        return program;
    }

    @Override
    public void setWorkout(DayOfWeek day, Workout workout) {

        if (day == null || workout == null) {
            throw new IllegalArgumentException();
        }

        program.put(day, workout);
    }

    @Override
    public Collection<DayOfWeek> getDaysFinishingWith(String exerciseName) {
        if (exerciseName == null || exerciseName.isEmpty() || exerciseName.isBlank()) {
            throw new IllegalArgumentException();
        }

        Set<DayOfWeek> days = new HashSet<>();
        for (DayOfWeek day:program.keySet()) {
            SequencedCollection<Exercise> list = program.get(day).exercises();
            if (list.getLast().name().equals(exerciseName)) {
                days.add(day);
            }
        }
        return days;
    }

    @Override
    public void addExercise(DayOfWeek day, Exercise exercise) {
        if (day == null || exercise == null) {
            throw new IllegalArgumentException();
        }

        if (program.get(day) == null) {
            throw new DayOffException();
        }

        program.putIfAbsent(day, new Workout(new ArrayList<>()));
        program.get(day).exercises().add(exercise);
    }

    @Override
    public void addExercises(DayOfWeek day, List<Exercise> exercises) {
        if (day == null || exercises == null || exercises.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (program.get(day) == null) {
            throw new DayOffException();
        }

        program.putIfAbsent(day, new Workout(new ArrayList<>()));
        program.get(day).exercises().addAll(exercises);
    }
}
