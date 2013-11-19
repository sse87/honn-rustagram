package controllers;

import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.service.RustagramService;

import play.cache.Cache;
import play.data.*;
import play.mvc.*;

import static play.data.Form.form;

import views.html.*;
import views.html.index;
import views.html.json;
import views.html.login;
import views.html.signup_success;

import java.util.List;

public class Images extends AbstractRustagramController {

    final static Form<Image> imageForm = form(Image.class);

    public static Result getAllImages() {

        RustagramService service = (RustagramService) ctx.getBean("service");
        //List<Image> images = service.getImages();



        return ok(views.html.json.render("test"));
    }

    public static Result showImageForm(){
        return ok(addimage.render(imageForm));
    }

    public static Result processImageForm(){
        Form<Image> filledForm = imageForm.bindFromRequest();
        RustagramService service = (RustagramService) ctx.getBean("service");
        Image img = filledForm.get();

        Image image = service.createImage(session().get("username"),"imgurl","description");

        return ok(index.render());
    }
}
