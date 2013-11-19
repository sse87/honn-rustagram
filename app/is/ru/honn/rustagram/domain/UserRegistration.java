package is.ru.honn.rustagram.domain;

import java.util.Date;

public class UserRegistration extends User
{
    protected String repeatPassword;
    public UserRegistration()
    {
    }
    public UserRegistration(int id, String name, String username, String password,
                            String email, String repeatPassword, Gender gender, Date registered)
    {
        super(id, name, username, password, email, gender, registered);
        this.repeatPassword = repeatPassword;
    }
    public UserRegistration(String name, String username, String password,
                            String email, String repeatPassword, Gender gender)
    {
        super( name, username, password, email, gender);
        this.repeatPassword = repeatPassword;
    }
    public String getRepeatPassword()
    {
        return repeatPassword;
    }
    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }
}
