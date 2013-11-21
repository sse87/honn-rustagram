package controllers;

import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserRegistration;
import is.ru.honn.rustagram.service.RustagramService;
import is.ru.honn.rustagram.data.UserData;

import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ru.honn.rustagram.service.UsernameExistsException;
import play.cache.Cache;
import play.data.*;
import play.mvc.*;

import static play.data.Form.form;

import views.html.index;
import views.html.login;
import views.html.signup;
import views.html.signup_success;

/**
 * A controller for user login and signup.
 */
public class Users extends AbstractRustagramController {

    //The form needed to be filled out to login.
    final static Form<User> loginForm = form(User.class);
    //The form needed to be filled out to signup.
    final static Form<UserRegistration> SignupForm = form(UserRegistration.class);

    /**
     * Displays the SignupForm so the user can put in his information.
     * @return Returns the form to the signup view with status code 200 OK.
     */
    public static Result showSignupForm(){
        return ok(signup.render(SignupForm));
    }

    /**
     * The process of reading the signupForm and creating the user if everything seems to be in order.
     * @return Returns the newly created user signup_success view with status code 200 OK.
     * If the form was not filled out correcly it rejects the form and returns back to the signupForm with helpful error messages.
     */
    public static Result processSignupForm(){
        Form<UserRegistration> filledForm = SignupForm.bindFromRequest();
        RustagramService service = (RustagramService) ctx.getBean("service");

        User user = null;
        try {
            user = service.getUser(filledForm.field("username").value());
        }
        catch (UserNotFoundException ex) {}

        if (user != null);
        {
            filledForm.reject("username", "This username is taken, please choose another one");
        }

        if (filledForm.field("username").value().length () < 3)
        {
            filledForm.reject("username", "Username must be at least 3 characters");
        }

        if (!filledForm.field("password").value().equals(filledForm.field("repeatPassword").value()))
        {
            filledForm.reject("repeatPassword", "Password does not match");
        }

        if (filledForm.field("password").value().length() < 4)
        {
            filledForm.reject("password", "Password too short");
        }
        if (filledForm.hasErrors())
        {
            return badRequest(signup.render(filledForm));
        }
        else{

            User created = filledForm.get();
            System.out.println(created);

            user = service.userSignup(created);

            return ok(signup_success.render(user));
        }
    }

    /**
     * Displays the loginForm so the user can login.
     * @return Returns the form to the login view with status code 200 OK.
     */
    public static Result showLoginForm(){
        return ok(login.render(loginForm));
    }

    /**
     * The process of reading the loginForm and loging in  the user if everything seems to be in order.
     * @return Returns the loged in user to his frontpage.
     * If the form was not filled out correcly it rejects the form and returns back to the loginForm with helpful error messages.
     */
    public static Result processLoginForm(){
        Form<User> filledForm = loginForm.bindFromRequest();

        // We get the context from AbstractRustagramController
        RustagramService service = (RustagramService) ctx.getBean("service");

        try{
            User user = service.getUser(filledForm.field("username").value());
            if(!user.getPassword().equals(filledForm.field("password").value())){
                // Let's throw this exception here to use the same logic for
                // unsuccessful login (both username not found and incorrect
                // password.
                throw new UserNotFoundException();
            }

            // User was found and correct password entered.
            session("username", user.getUsername());
            session("displayName", user.getDisplayName());

        }
        catch(UserNotFoundException unfe){
            filledForm.reject("password", "User not found or incorrect password entered.");
            return badRequest(login.render(filledForm));
        }

        return redirect("index");
    }

    /**
     * Loges out the user.
     * @return Returns to the front page with no user loged in.
     */
    public static Result logout(){
        session().clear();
        return redirect("index");
    }
}
