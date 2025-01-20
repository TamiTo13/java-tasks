package bg.sofia.uni.fmi.mjt.gym;

import bg.sofia.uni.fmi.mjt.gym.member.Address;
import bg.sofia.uni.fmi.mjt.gym.member.GymMember;
import bg.sofia.uni.fmi.mjt.gym.workout.Exercise;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Gym implements GymAPI {

    final int capacity;
    public Address address;
    TreeSet<GymMember> members;
    private int current;

    public Gym(int capacity, Address address) {
        this.capacity = capacity;
        this.address = address;
        members = new TreeSet<>(new GymMemberComparator());
        current = 0;
    }

    @Override
    public SortedSet<GymMember> getMembers() {

        final TreeSet<GymMember> ret = new TreeSet<>(members);
        return ret;
    }

    @Override
    public SortedSet<GymMember> getMembersSortedByName() {
        GymMemberComparator a = new GymMemberComparator();
        final TreeSet<GymMember> ret = new TreeSet<>(a);
        ret.addAll(members);
        return ret;
    }

    @Override
    public SortedSet<GymMember> getMembersSortedByProximityToGym() {
        final TreeSet<GymMember> ret = new TreeSet<>(new ProximityComparator(address));
        if (members.isEmpty()) {
            return ret;
        }
        ret.addAll(members);
        return ret;
    }

    @Override
    public void addMember(GymMember member) throws GymCapacityExceededException {
        if (member == null) {
            throw new IllegalArgumentException();
        }
        if (current >= capacity) {
            throw new GymCapacityExceededException();
        }
        members.add(member);
        current++;
    }

    @Override
    public void addMembers(Collection<GymMember> members) throws GymCapacityExceededException {

        if (members == null || members.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (members.size() + current > capacity) {
            throw new GymCapacityExceededException();
        }
        this.members.addAll(members);
    }

    @Override
    public boolean isMember(GymMember member) {
        if (member == null) throw new IllegalArgumentException();
        return members.contains(member);
    }

    @Override
    public boolean isExerciseTrainedOnDay(String exerciseName, DayOfWeek day) {
        if (exerciseName == null || exerciseName.isBlank() || exerciseName.isEmpty()
            || day == null) {
            throw new IllegalArgumentException();
        }
        for (GymMember member: members) {
            for (Exercise exercise : member.getTrainingProgram().get(day).exercises()) {
                if (exercise.name().equals(exerciseName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Map<DayOfWeek, List<String>> getDailyListOfMembersForExercise(String exerciseName) {

        if (exerciseName == null || exerciseName.isEmpty() || exerciseName.isBlank()) {
            throw new IllegalArgumentException();
        }

        //Map<DayOfWeek, List<String>> first = new HashMap<>();
        final Map<DayOfWeek, List<String>> ret = new HashMap<>();
        for (DayOfWeek day: DayOfWeek.values()) {
            for (GymMember member : members) {
                if (!member.getTrainingProgram().containsKey(day)) {
                    continue;
                }
                for (Exercise exercise : member.getTrainingProgram().get(day).exercises()) {
                    if (exercise.name().equals(exerciseName)) {
                        ret.putIfAbsent(day, new ArrayList<>());
                        ret.get(day).add(member.getName());
                    }
                }
            }
            //ret.putIfAbsent(day,first.get(day));
        }

        return ret;
    }

}
