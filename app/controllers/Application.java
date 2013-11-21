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
import java.util.Map;
import java.util.Iterator;
import com.fasterxml.jackson.databind.JsonNode;

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

    public static Result addComment() {

        //JsonNode json = request().body().asJson();

        //RustagramService service = (RustagramService) ctx.getBean("service");

        //System.out.println("request: " + json.textValue());

        //System.out.println(".1..: " + request().body());
        //System.out.println(".2..: " + request().body().asJson().get("comment"));


        /*Map<String, String[]> map = request().body().asFormUrlEncoded();

        Iterator i = map.entrySet().iterator();

        while(i.hasNext())
        {
            Entry e = i.next();

            String key = e.getKey();
            String value = e.getValue();

            System.out.println("work mofo: " + key + " - " + value);
        }*/

        //System.out.println("work mofo: " + request().getQueryString("imageId"));
        //System.out.println("work mofo: " + request().getQueryString("comment"));

        /*for (String s : map.values()) {
            System.out.println("test: " + s);
        }*/

        /*for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }*/


        /*for (String val : map) {
            System.out.println("request: " + val);
        }*/

        /*
        String username = form().bindFromRequest().get("username");
        int imageId = Integer.parseInt(form().bindFromRequest().get("imageId"));
        String comment = form().bindFromRequest().get("comment");

        service.addCommentOnImage(username, imageId, comment);
        */
        return ok("asdf");
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
