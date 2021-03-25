package models;

public class UserPutResponseBody {

    private String updatedAt;

    private String name;

    private String job;

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getJob ()
    {
        return job;
    }

    public void setJob (String job)
    {
        this.job = job;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [updatedAt = "+updatedAt+", name = "+name+", job = "+job+"]";
    }
}

