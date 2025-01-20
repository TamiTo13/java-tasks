package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;

public class StandardAccount extends AccountBase{

    static final AccountType ACCOUNT_TYPE = AccountType.STANDARD;

    public StandardAccount(String username, double balance) {
        super(username, balance);
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {
        if(course.getPrice() > balance) {
            throw new InsufficientBalanceException();
        }

        for (int i = 0; i < current; i++) {
            if (course.getName().equals(courses[i].getName())) {
                throw new CourseAlreadyPurchasedException();
            }
        }

        if(current >= MAX_COURSES) {
            throw new MaxCourseCapacityReachedException();
        }

        courses[current++] = course;
        balance -= course.getPrice();
    }
}
