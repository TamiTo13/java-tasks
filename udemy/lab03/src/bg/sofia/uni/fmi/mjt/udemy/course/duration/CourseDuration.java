package bg.sofia.uni.fmi.mjt.udemy.course.duration;

import bg.sofia.uni.fmi.mjt.udemy.course.Resource;

public record CourseDuration(int hours, int minutes) {

    public CourseDuration {
        if (hours < 0 || hours > 24 || minutes < 0 || minutes >60) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isLonger(CourseDuration o1, CourseDuration o2) {
         return (o1.hours*60 + o1.minutes) > (o2.hours*60 + o2.minutes);
    }

    public static CourseDuration of(Resource[] content) {
        if(content == null ) return new CourseDuration(0, 0);

        int minutes = 0;
        for(Resource iter: content) {
            minutes += iter.getDuration().minutes();
        }

        return new CourseDuration(minutes%60, minutes/60);
    }
}
