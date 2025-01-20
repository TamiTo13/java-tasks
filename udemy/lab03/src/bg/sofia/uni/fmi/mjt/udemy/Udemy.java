package bg.sofia.uni.fmi.mjt.udemy;

import bg.sofia.uni.fmi.mjt.udemy.account.Account;
import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.course.duration.CourseDuration;
import bg.sofia.uni.fmi.mjt.udemy.exception.AccountNotFoundException;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotFoundException;

public class Udemy implements LearningPlatform {

    Account[] accounts;
    private int accSize;
    Course[] courses;
    private int courSize;


    public Udemy(Account[] accounts, Course[] courses) {
            this.accounts = new Account[accounts.length];
            //System.out.println(this.accounts.length);
            //accSize = accounts.length;
            for (int i = 0; i < accounts.length; i++) {
                this.accounts[i] = accounts[i];
                //System.out.println(accounts[i]);
            }

            this.courses = new Course[courses.length];
            //System.out.println(this.courses.length);
            for (int i = 0; i < courses.length; i++) {
                this.courses[i] = courses[i];
                //System.out.println(courses[i]);
            }
    }

    @Override
    public Course findByName(String name) throws CourseNotFoundException {

        if(name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException();

        for(Course course: courses) {
            if(name.equals(course.getName())) return course;
        }

        throw new CourseNotFoundException();
    }

    @Override
    public Course[] findByKeyword(String keyword) {
        if(keyword == null || keyword.isBlank() || !keyword.chars().allMatch(Character::isLetter ))
            throw new IllegalArgumentException();

        int arr_size = 0;
        for(Course course: courses) {
            // System.out.println("kur");
           if(course.getName().contains(keyword) ||
                    course.getDescription().contains(keyword)) arr_size++;
        }

        if(arr_size==0) {
            return new Course[0];
        }

        Course[] returnArr = new Course[arr_size];
        int i = 0;
        for(Course course: courses) {
            if(course.getName().contains(keyword) ||
                    course.getDescription().contains(keyword)) {
                returnArr[i++] = course;
            }
        }

        return returnArr;
    }

    @Override
    public Course[] getAllCoursesByCategory(Category category) {

        if(category == null) {
            throw new IllegalArgumentException();
        }

        int size =0;
        for(Course course: courses) {
            if(course.getCategory() == category) {
                size++;
            }
        }

        if(size == 0) return new Course[0];

        Course[] returnArr = new Course[size];
        int i =0;
        for(Course course: courses) {
            if(course.getCategory() == category) {
                returnArr[i++] = course;
            }
        }
        return returnArr;

    }

    @Override
    public Account getAccount(String name) throws AccountNotFoundException {
        if(name == null || name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for(Account account:accounts) {
            if(account.getUsername().equals(name)) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public Course getLongestCourse() {
        if(courses == null) return null;
        Course ret = courses[0];
        for(int i = 1; i<courses.length; i++) {
            if(!CourseDuration.isLonger(ret.getTotalTime(), courses[i].getTotalTime())) {
                ret = courses[i];
            }
        }
        return ret;
    }

    @Override
    public Course getCheapestByCategory(Category category) {
        if(category == null) throw new IllegalArgumentException();

        int size =0;
        Course[] arr = new Course[courses.length];

        for(Course course:courses) {
            if(course.getCategory() == category)
            {
                arr[size++] = course;
            }
        }

        if(size == 0) return null;
        Course cheapest = arr[0];
        for(int i =1; i<size; i++) {
            if(arr[i].getPrice() < cheapest.getPrice()) {
                arr[i] = cheapest;
            }
        }
        return cheapest;
    }
}
