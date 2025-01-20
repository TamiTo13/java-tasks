import bg.sofia.uni.fmi.mjt.udemy.Udemy;
import bg.sofia.uni.fmi.mjt.udemy.account.Account;
import bg.sofia.uni.fmi.mjt.udemy.account.BusinessAccount;
import bg.sofia.uni.fmi.mjt.udemy.account.EducationalAccount;
import bg.sofia.uni.fmi.mjt.udemy.account.StandardAccount;
import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.course.Resource;
import bg.sofia.uni.fmi.mjt.udemy.course.duration.ResourceDuration;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotFoundException;

public class Main {

    public static void main(String[] args) {

        Category[] categories = new Category[2];
        categories[0] = Category.BUSINESS;
        categories[1] = Category.DESIGN;

        Account[] accounts = new Account[3];
        accounts[0] = new EducationalAccount("acc1",100);
        accounts[1] = new StandardAccount("acc2", 110);
        accounts[2] = new BusinessAccount("acc3", 120,categories);


        Resource[] content = new Resource[2];
        content[0] = new Resource("res1",new ResourceDuration(30));
        content[1] = new Resource("res2",new ResourceDuration(30));

        Course[] courses = new Course[2];
        courses[0] = new Course("Math", "nz neshto", 40, content, Category.BUSINESS);
        courses[1] = new Course("DIS", "nz nessadashto", 40, content, Category.DESIGN);

        Udemy a = new Udemy(accounts, courses);
        a.findByKeyword("nz");
        try {
        accounts[0].buyCourse(courses[0]);
        accounts[1].buyCourse(courses[1]);
        accounts[2].buyCourse(courses[1]);
        } catch (Throwable e) {
            System.out.println(e);
        }
        try{
            accounts[0].completeResourcesFromCourse(courses[0], content);
        } catch (Throwable e) {
            System.out.println(e);
        }

        }
}

