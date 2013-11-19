package controllers;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.service.RustagramService;

import play.cache.Cache;
import play.data.*;
import play.mvc.*;

import static play.data.Form.form;

import views.html.index;
import views.html.json;

import java.util.List;

public class Images extends AbstractRustagramController {

    public static Result getAllImages() {

        RustagramService service = (RustagramService) ctx.getBean("service");
        //List<Image> images = service.getImages();



        return ok(views.html.json.render("test"));
    }
}
