package bg.sofia.uni.fmi.mjt.udemy.course;

import bg.sofia.uni.fmi.mjt.udemy.course.duration.CourseDuration;
import bg.sofia.uni.fmi.mjt.udemy.exception.ResourceNotFoundException;

public class Course implements Completable, Purchasable{
    String name;
    String description;
    double price;
    Category category;
    Resource[] content;
    int contentSize;
    boolean completed = false;
    boolean purchased = false;
    public Double grade = 0.0;

    CourseDuration courseDuration;

    public Course(String name, String description, double price, Resource[] content, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        contentSize = 0;
        this.content = new Resource[content.length];

        int time = 0;
        for(Resource iter: content) {
            this.content[contentSize++] = iter;
            time += iter.duration.minutes();
        }

        courseDuration = new CourseDuration(time/60,time%60);

    }

    public boolean check(Resource[] arr) {
        boolean check;
        for(Resource component:arr) {
            check = false;
            for(Resource iter:content) {
                if(component.getName().equals(iter.getName())) {
                    check = true;
                    continue;
                }
            }
            if(!check) {
                System.out.println(component);
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the course.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the course.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the category of the course.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the content of the course.
     */
    public Resource[] getContent() {
        return content;
    }

    /**
     * Returns the total duration of the course.
     */
    public CourseDuration getTotalTime() {
        return courseDuration;
    }

    /**
     * Completes a resource from the course.
     *
     * @param resourceToComplete the resource which will be completed.
     * @throws IllegalArgumentException if resourceToComplete is null.
     * @throws ResourceNotFoundException if the resource could not be found in the course.
     */
    public void completeResource(Resource resourceToComplete) throws ResourceNotFoundException {
            if(resourceToComplete == null) throw new IllegalArgumentException();
            for(Resource resource:content) {
                if(resource.getName().equals(resourceToComplete.getName())) {
                    resource.complete();
                    return;
                }
            }
            throw new ResourceNotFoundException();
    }

    @Override
    public boolean isCompleted() {

        for(Resource resource:content) {
            if(!resource.isCompleted()) return false;
        }
        return true;
    }

    @Override
    public int getCompletionPercentage() {
        int count = 0;
       for(Resource resource:content) {
           if(resource.isCompleted()) {
               count++;
           }
       }
       return (int)Math.round(((double)count/content.length)*100);
    }

    @Override
    public void purchase() {
        purchased = true;
    }

    @Override
    public boolean isPurchased() {
         return purchased;
    }
}
