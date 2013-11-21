package is.ru.honn.rustagram.domain;

import java.util.Date;
import java.util.List;

public class Feed extends Image
{
    protected String creatorDisplayName;
    protected List<String> likers;
    protected List<Comment> comments;

    Feed() {}

    public Feed(int id, String creator, String url, String description) {
        super(id, creator, url, description);
    }

    public Feed(String creator, String creatorDisplayName, String url, String description) {
        super(creator, url, description);
    }

    public Feed(int id, String creator, String creatorDisplayName, Date created, String url, String description) {
        super(id, creator, created, url, description);
    }

    public Feed(Image i) {
        super(i.getId(), i.creatorUsername, i.getCreated(), i.getUrl(), i.getDescription());
    }

    public String getCreatorDisplayName()
    {
        return creatorDisplayName;
    }

    public void setCreatorDisplayName(String creatorDisplayName)
    {
        this.creatorDisplayName = creatorDisplayName;
    }

    public List<String> getLikers() {
        return likers;
    }

    public void setLikers(List<String> likers) {
        this.likers = likers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
