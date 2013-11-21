package controllers;

import is.ru.honn.rustagram.domain.Feed;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.service.RustagramService;

import play.cache.Cache;
import play.data.*;
import play.mvc.*;

import static play.data.Form.form;

import views.html.*;
import views.html.imageInfo;
import views.html.index;
import views.html.login;
import views.html.signup_success;
import views.html.upload_success;

import java.util.List;

import static play.libs.Json.toJson;

public class Images extends AbstractRustagramController {

    final static Form<Image> imageForm = form(Image.class);

    public static Result getAllImages() {

        RustagramService service = (RustagramService) ctx.getBean("service");

        List<Image> images = service.getAllImages();

        return ok(toJson(images));
    }

    public static Result showImageForm(){
        return ok(addimage.render(imageForm));
    }

    public static Result processImageForm(){
        Form<Image> filledForm = imageForm.bindFromRequest();
        RustagramService service = (RustagramService) ctx.getBean("service");


        Image image = service.createImage(session().get("username"),filledForm.field("imgurl").value(), filledForm.field("description").value());

        return ok(upload_success.render(image));
    }

    public static Result showImageInfo(String strId){

        RustagramService service = (RustagramService) ctx.getBean("service");

        int id = Integer.parseInt(strId);
        Feed feed = new Feed(service.getImage(id));

        feed.setComments(service.getCommentsOnImage(id));

        return ok(imageInfo.render(feed));
    }

    public static Result getTotalLikes(String imageId) {

        RustagramService service = (RustagramService) ctx.getBean("service");

        int id = Integer.parseInt(imageId);

        int totalLikes = service.getLikesOnImage(id);

        return ok(toJson(totalLikes));
    }

}
