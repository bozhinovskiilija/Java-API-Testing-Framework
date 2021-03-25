package models;

public class UserPostRequestBody {
    private String name;

    private String job;

    public String getName() {
        return name;
    }

    public UserPostRequestBody withName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return job;
    }

    public UserPostRequestBody withJob(String job) {
        this.job = job;
        return this;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", job = " + job + "]";
    }
}