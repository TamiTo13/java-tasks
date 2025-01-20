package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;

import static bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType.EDUCATION;

public class EducationalAccount extends  AccountBase{

    static final AccountType ACCOUNT_TYPE = EDUCATION;
    public EducationalAccount(String username, double balance) {
        super(username, balance);
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {

        if(current >= MAX_COURSES) {
            throw new MaxCourseCapacityReachedException();
        }

        for (int i = 0; i < current; i++) {
            if (course.getName().equals(courses[i].getName())) {
                throw new CourseAlreadyPurchasedException();
            }
        }


        if(current < 5 && balance >= course.getPrice()) {
            courses[current++] = course;
            balance -= course.getPrice();
            return;
        } else if (balance < course.getPrice()) {
            throw new InsufficientBalanceException();
        }

        //tup spageti kod ne go pipay
        boolean discount = true;
        for(int i = 1; i<=5; i++) {
            if(courses[current-i].grade == 0.0) {
               discount=false;
            }
        }
        if(discount) {
            double grade = 0.0;
            for(int i = 1; i<=5; i++) {
                grade += courses[current-i].grade;
            }
            grade /= 5;
            discount = grade >= 4.5;
        }
        Double price = course.getPrice() - (discount?
                 course.getPrice() * ACCOUNT_TYPE.getDiscount() : 0);

        if(balance < price) {
            throw new InsufficientBalanceException();
        }

        courses[current++] = course;
        balance -= price;
    }
}
