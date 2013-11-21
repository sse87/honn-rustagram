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

/**
 * Image controller
 */
public class Images extends AbstractRustagramController {

    //Creates a form so the user can link images to Rustagram.
    final static Form<Image> imageForm = form(Image.class);

    /**
     * Makes a list with all the images in Rustagram.
     * @return Returns the image list to toJson
     */
    public static Result getAllImages() {

        RustagramService service = (RustagramService) ctx.getBean("service");

        List<Image> images = service.getAllImages();

        return ok(toJson(images));
    }

    /**
     * Displays the imageForm
     * @return Returns the form to the addimage view with status code 200 OK.
     */
    public static Result showImageForm(){
        return ok(addimage.render(imageForm));
    }

    /**
     * Processes the filled out imageForm
     * If the URL is not valid it will return a blank image.
     * @return Returns the image uploaded to the upload_success view.
     */
    public static Result processImageForm(){
        Form<Image> filledForm = imageForm.bindFromRequest();
        RustagramService service = (RustagramService) ctx.getBean("service");


        Image image = service.createImage(session().get("username"),filledForm.field("imgurl").value(), filledForm.field("description").value());

        return ok(upload_success.render(image));
    }

    /**
     * Displays the info for the image clicked on.
     * @param id Takes in a id for an image
     * @return Returns the feed for the image with the id taken in.
     */
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
