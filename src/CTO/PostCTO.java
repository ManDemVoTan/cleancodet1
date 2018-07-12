package CTO;

import model.Post;
import util.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

//CTO là CoverToObject
public class PostCTO {
    private static PostCTO instance;
    public static PostCTO getInstance(){
        if ( instance==null )instance = new PostCTO ();
        return instance;
    }
    private List<Post> postList;

    public List<Post> getPostList() {
        if ( postList==null ) {
            postList = openFile ();
        }
        postList.forEach (post ->
        {
            post.setLikeOfPost ( LikeOfPostCTO.getInstance ().getByPostId (post.getId ()) );
            post.setCommentOfPost (CommentOfPostCTO.getIntance ().getByPostId (post.getId ()));
        });
        return postList;
    }
    public Post add(Post post){
        int maxId = 0;
        if ( postList==null )postList = openFile ();
       if ( postList==null )postList = new ArrayList<> ();
           for (Post p : postList){
            if ( p.getId ()>maxId )maxId=p.getId ()+1;
        }

        post.setId (maxId);
        postList.add (post);
        return post;
    }
    public void remove(Post post){
        postList.remove (post);
    }

    public void close(){
        saveFile (postList);
    }
    private   List<Post> openFile(){
        List<Post> posts = new ArrayList<> ();
        Matcher matcher = File.getInstance ().getMacher ( File.getInstance ().PATH_POST,
                File.getInstance ().POST_REGEX);
       if ( matcher==null ) return null;
        while (matcher.find ()){
            Post post = new Post ();
            post.setId (Integer.parseInt (matcher.group ("id")));
            post.setUsername (matcher.group ("username"));
            post.setContent (matcher.group ("content"));
            posts.add (post);
        }
        return posts;
    }

    private void saveFile(List<Post> posts) {
        StringBuilder stringBuilder = new StringBuilder ();
        posts.forEach (post -> stringBuilder.append (post.ObectToString ()));
        File.getInstance ().writeFile (stringBuilder,
                File.getInstance ().PATH_POST);
    }

}
