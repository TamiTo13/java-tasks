package bg.sofia.uni.fmi.mjt.gym.workout;

public record Exercise(String name, int sets, int repetitions) {
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o1) {
        return this.hashCode() == o1.hashCode();
    }
}
