package controllers;

import is.ru.honn.rustagram.domain.Feed;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.service.RustagramService;
import play.*;
import play.mvc.*;

import views.html.*;
import views.html.imageInfo;
import views.html.login;

import java.util.List;
import java.util.ArrayList;

import static play.libs.Json.toJson;

/**
 * Application controller
 */
public class Application extends AbstractRustagramController {

    /**
     * Renders the front page for our application.
     * @return Returns feeds to the index view with status code 200 OK.
     */
    public static Result index() {

        RustagramService service = (RustagramService) ctx.getBean("service");
        // Creates a list of all images that have been uploaded on to Rustagram.
        java.util.List<Image> images = service.getAllImages();
        // Creates a list of feeds we would like to use
        java.util.List<Feed> feeds = new ArrayList<Feed>();
        for (Image image : images) {

            Feed feed = new Feed(image);

            User user = service.getUser(feed.getCreatorUsername());
            if (user != null) {
                feed.setCreatorDisplayName(user.getDisplayName());
            }
            feed.setComments(service.getCommentsOnImage(feed.getId()));

            feeds.add(feed);
        }

        return ok(index.render(feeds));
    }

    /**
     *
     * @return
     */
    public static Result AddComment() {

        RustagramService service = (RustagramService) ctx.getBean("service");

        //System.out.println("request: " + request().body().get("comment"));

        /*
        String username = form().bindFromRequest().get("username");
        int imageId = Integer.parseInt(form().bindFromRequest().get("imageId"));
        String comment = form().bindFromRequest().get("comment");

        service.addCommentOnImage(username, imageId, comment);
        */
        return ok(toJson("works"));
    }

    /**
     * Renders the front page for our application.
     * @return Returns feeds to Json with status code 200 OK.
     */
    public static Result getFeeds() {

        RustagramService service = (RustagramService) ctx.getBean("service");

        List<Image> images = service.getAllImages();

        List<Feed> feeds = new ArrayList<Feed>();
        for (Image image : images) {

            Feed feed = new Feed(image);

            User user = service.getUser(feed.getCreatorUsername());
            if (user != null) {
                feed.setCreatorDisplayName(user.getDisplayName());
            }
            feed.setComments(service.getCommentsOnImage(feed.getId()));

            feeds.add(feed);
        }

        return ok(toJson(feeds));
    }

}
