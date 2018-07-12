package model;



public class LikeOfPost {
    private int id;
    private String username;
    private int postId;

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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String ObjectToString(){
        System.out.println ("Call");
        return "id : "+id+"\nusername : "+username+"\npost_id : "+postId+"\n";
    }

    @Override
    public String toString() {
        return "LikeOfPost{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", postId=" + postId +
                '}';
    }
}
