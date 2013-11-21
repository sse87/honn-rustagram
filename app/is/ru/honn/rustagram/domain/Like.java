package is.ru.honn.rustagram.domain;

import java.util.Date;

/**
 * A Like.
 */
public class Like extends RustagramObject {
    private int imageId;

    public Like() {}

    public Like(String creator, int imageId) {
        super(creator);
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
