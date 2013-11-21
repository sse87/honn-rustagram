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

public class Application extends AbstractRustagramController {

    public static Result index() {

        RustagramService service = (RustagramService) ctx.getBean("service");

        java.util.List<Image> images = service.getAllImages();

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
