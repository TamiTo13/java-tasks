package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.course.Resource;
import bg.sofia.uni.fmi.mjt.udemy.exception.*;

public abstract class AccountBase implements Account {

    String username;
    Double balance;
    Course[] courses;
    int current;
    public static final int MAX_COURSES = 100;


    public AccountBase(String username, double balance) {
        this.balance = balance;
        this.username = username;
        courses = new Course[MAX_COURSES];
        current = 0;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void addToBalance(double amount) {
        if(amount < 0.0) throw new IllegalArgumentException();
        balance +=amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public abstract void buyCourse(Course course) throws InsufficientBalanceException,
            CourseAlreadyPurchasedException, MaxCourseCapacityReachedException;

    @Override
    public void completeResourcesFromCourse(Course course, Resource[] resourcesToComplete)
            throws CourseNotPurchasedException, ResourceNotFoundException {
            if(course == null || resourcesToComplete == null) {
                throw new IllegalArgumentException();
            }

            boolean check = false;

            for(int i = 0; i<current;i++) {
                if(courses[i].getName().equals(course.getName())) {
                    check = true;
                    break;
                }
            }
            if(!check) {
                throw new CourseNotPurchasedException();
            }
            if(!course.check(resourcesToComplete)) throw new ResourceNotFoundException();

            for(Resource resource:resourcesToComplete) {
                course.completeResource(resource);
            }
    }

    @Override
    public void completeCourse(Course course, double grade)
            throws CourseNotPurchasedException, CourseNotCompletedException {
            if(course == null || grade < 2.0 || grade > 6.0) {
                throw new IllegalArgumentException();
            }

        boolean check = false;
        for(int i =0; i<current; i++) {
            if(courses[i].getName().equals(course.getName())) {
                check = true;
                course = courses[i];
                break;
            }
        }
        if(!check) {
            throw new CourseNotPurchasedException();
        }

        for(Resource resource:course.getContent()) {
            if(!resource.isCompleted()) {
                throw new CourseNotCompletedException();
            }
        }

        course.grade =grade;
    }

    @Override
    public Course getLeastCompletedCourse() {
        if(courses == null) return null;

        int index = 0;
        for(int i = 1; i<current; i++) {
            if(courses[index].getCompletionPercentage()>courses[i].getCompletionPercentage()) {
                courses[index] = courses[i];
            }
        }
        return courses[index];
    }
}
