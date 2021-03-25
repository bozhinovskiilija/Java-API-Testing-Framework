package models;

public class UserPutRequestBody {
    private String name;

    private String job;

    public String getName() {
        return name;
    }

    public UserPutRequestBody withName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return job;
    }

    public UserPutRequestBody withJob(String job) {
        this.job = job;
        return this;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", job = " + job + "]";
    }
}
