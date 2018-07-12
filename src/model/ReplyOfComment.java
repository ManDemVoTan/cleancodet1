package model;

public class ReplyOfComment {
    private int id;
    private int commentId;
    private String username;
    private String content;

    public String ObjectToString(){
        return "id : "+id+"\nusername : "
                +username+"\ncontent : "
                +content+"\ncomment_id : "
                +commentId+"\n";
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReplyOfComment{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
