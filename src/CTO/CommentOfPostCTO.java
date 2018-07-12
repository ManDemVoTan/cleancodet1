package CTO;

import model.CommentOfPost;
import model.LikeOfPost;
import util.File;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class CommentOfPostCTO {
    private static CommentOfPostCTO intance;
    public static CommentOfPostCTO getIntance(){
        if ( intance==null )intance = new CommentOfPostCTO ();
        return intance;
    }
    private List<CommentOfPost> commentOfPosts;
    public List<CommentOfPost> getCommentOfPosts(){
        if ( commentOfPosts==null)commentOfPosts = openFile ();
        commentOfPosts.forEach (commentOfPost -> {
            System.out.println (commentOfPost + " is comment of post");
            commentOfPost.setLikeOfComment (LikeOfCommentCTO.getInstance ().getByCommentId (commentOfPost.getId ()));
            commentOfPost.setReplyOfComment (ReplyOfCommentCTO.getInstance ().getByCommentId (commentOfPost.getId ()));
        });
        return commentOfPosts;
    }
    public  List<CommentOfPost> getByPostId(int postId){

        List<CommentOfPost> result = new ArrayList<> ();
       if ( commentOfPosts==null ) commentOfPosts = openFile ();
           commentOfPosts.forEach (commentOfPost -> {
            if ( commentOfPost.getId ()==postId )result.add (commentOfPost);
        });
        return result;
    }
    public CommentOfPost getById(int id){
        for (CommentOfPost commentOfPost : commentOfPosts){
            if ( commentOfPost.getId ()==id )return commentOfPost;
        }
        return null;
    }

    public CommentOfPost add(CommentOfPost commentOfPost){
        if ( commentOfPosts==null )commentOfPosts =openFile ();
        int maxId = 0;
      if ( commentOfPosts==null )commentOfPosts = new ArrayList<> ();
          for (CommentOfPost c : commentOfPosts){
            if ( c.getId ()>maxId )maxId=c.getId ()+1;
        }
        commentOfPost.setId (maxId);
        commentOfPosts.add (commentOfPost);
       return commentOfPost;
    }
    public void remove(CommentOfPost commentOfPost){
        commentOfPosts.remove (commentOfPost);
    }
    private List<CommentOfPost> openFile(){
        System.out.println ("calll open file");
            List<CommentOfPost> commentOfPosts = new ArrayList<> ();
            Matcher matcher = File.getInstance ().getMacher ( File.getInstance ().PATH_COMMENT_OF_POST,
                    File.getInstance ().COMMENT_OF_POST_REGEX);

        if ( matcher==null ) return null;
            while (matcher.find ()){
                CommentOfPost commentOfPost = new CommentOfPost ();
                commentOfPost.setId (Integer.parseInt (matcher.group ("id")));
                commentOfPost.setUsername (matcher.group ("username"));
                commentOfPost.setContent (matcher.group ("content"));
                commentOfPost.setPostId (Integer.parseInt (matcher.group ("postid")));
                commentOfPosts.add (commentOfPost);
            }
        System.out.println ("cmt is : ");
        System.out.println (commentOfPosts);
            return commentOfPosts;
    }
    public void close(){
        saveFile (commentOfPosts);
    }
    private void saveFile(List<CommentOfPost> commentOfPosts){
        StringBuilder stringBuilder = new StringBuilder ();
        commentOfPosts.forEach (commentOfPost -> stringBuilder.append (commentOfPost.ObjectToString ()));
        File.getInstance ().writeFile (stringBuilder,
                File.getInstance ().PATH_COMMENT_OF_POST);
    }
}
