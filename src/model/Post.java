package model;

import java.util.List;

public class Post {
    private int id;
    private String username;
    private String content;
    private List<LikeOfPost> likeOfPost;
    private List<CommentOfPost> commentOfPost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LikeOfPost> getLikeOfPost() {
        return likeOfPost;
    }

    public void setLikeOfPost(List<LikeOfPost> likeOfPost) {
        this.likeOfPost = likeOfPost;
    }

    public List<CommentOfPost> getCommentOfPost() {
        return commentOfPost;
    }

    public void setCommentOfPost(List<CommentOfPost> commentOfPost) {
        this.commentOfPost = commentOfPost;
    }
    public String ObectToString(){
        return "id : "+id+"\nusername : "+username+"\ncontent : "+content+"\n";
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", likeOfPost=" + likeOfPost +
                ", commentOfPost=" + commentOfPost +
                '}';
    }
}
