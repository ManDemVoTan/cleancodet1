package model;

public class LikeOfComment {
    private int id;
    private int commentId;
    private String username;

    public String ObjectToString(){
        return "id : "+id+"\nusername : "+username+"\ncomment_id : "+commentId+"\n";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LikeOfComment{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", username='" + username + '\'' +
                '}';
    }
}
