package fr.eni.android.helloworldandroidstudio.entity.user;

public class BO_User {

    public int id;
    public String firstName;
    public String lastName;
    public int color;

    public BO_User() {}

    public BO_User(String firstName, String lastName, int color)
    {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.color      = color;
    }

    @Override
    public String toString()
    {
        return "Nom : "+this.lastName+"\n"+
               "Prénom : "+this.firstName;
    }

}
