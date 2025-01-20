package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;

import static bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType.BUSINESS;

public class BusinessAccount extends AccountBase{

    //private Course[] courses;
    static final AccountType ACCTYPE = BUSINESS;
    int current;
    private  Category[] categories;


    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException,
            CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {

            boolean check = false; //false
            for(Category iter: categories) {
                if(course.getCategory() == iter) {
                    check = true;
                    break;
                }
            }
            if(!check) {
                throw new IllegalArgumentException();
            }

            if(course.getPrice() - (course.getPrice() * ACCTYPE.getDiscount()) > balance) {
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
            courses[current-1].purchase();
            balance -= course.getPrice() - (course.getPrice() * ACCTYPE.getDiscount());
    }

    public BusinessAccount(String username, double balance, Category[] allowedCategories) {
        super(username,balance);
        if(allowedCategories == null) {
            throw new IllegalArgumentException();
        }
        categories = new Category[allowedCategories.length];
        System.arraycopy(allowedCategories, 0, categories, 0, allowedCategories.length);
    }
}
