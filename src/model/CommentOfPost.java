package model;

import java.util.List;

public class CommentOfPost {
    private int id;
    private int postId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private String content;
    List<LikeOfComment> likeOfComment;
    List<ReplyOfComment> replyOfComment;

    public String ObjectToString(){
        return "id : "+id+"\nusername : "+username+"\ncontent : "+content+"\npost_id : "+postId+"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LikeOfComment> getLikeOfComment() {
        return likeOfComment;
    }

    public void setLikeOfComment(List<LikeOfComment> likeOfComment) {
        this.likeOfComment = likeOfComment;
    }

    public List<ReplyOfComment> getReplyOfComment() {
        return replyOfComment;
    }

    public void setReplyOfComment(List<ReplyOfComment> replyOfComment) {
        this.replyOfComment = replyOfComment;
    }

    @Override
    public String toString() {
        return "CommentOfPost{" +
                "id=" + id +
                ", postId=" + postId +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", likeOfComment=" + likeOfComment +
                ", replyOfComment=" + replyOfComment +
                '}';
    }
}
