package CTO;

import model.LikeOfPost;
import model.Post;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import util.File;

public class LikeOfPostCTO {
    private static LikeOfPostCTO instance;
    public static LikeOfPostCTO getInstance(){
        if ( instance ==null )
            instance=  new LikeOfPostCTO ();

        return instance;
    }
 private List<LikeOfPost> likeOfPosts;
 public List<LikeOfPost> getLikeOfPosts(){
     if ( likeOfPosts==null ) {
         likeOfPosts =  openFile ();
     }
     return likeOfPosts;
 }
 public LikeOfPost add(LikeOfPost likeOfPost){
     if ( likeOfPosts==null )likeOfPosts=openFile ();
     int maxId = 0;
    if ( likeOfPosts==null ) likeOfPosts = new ArrayList<> ();
      else   for (LikeOfPost l : likeOfPosts){
         if ( l.getId ()> maxId)maxId=l.getId ()+1;
     }
     likeOfPost.setId (maxId);
     likeOfPosts.add (likeOfPost);
     return likeOfPost;
 }
 public void remove(LikeOfPost likeOfPost){
     likeOfPosts.remove (likeOfPost);
 }

 public LikeOfPost getById(int id){
      for (LikeOfPost likeOfPost : likeOfPosts){
          if ( likeOfPost.getId ()==id )
              return likeOfPost;
      }
      return null;
 }


 public List<LikeOfPost> getByPostId(int postId){
     List<LikeOfPost> result = new ArrayList<> ();
     getLikeOfPosts().forEach (likeOfPost -> {
         if ( likeOfPost.getPostId ()==postId )result.add (likeOfPost);
     });
     return result;
 }
 private List<LikeOfPost> openFile()  {
     List<LikeOfPost> likeOfPosts = new ArrayList<> ();
     Matcher matcher = File.getInstance ().getMacher ( File.getInstance ().PATH_LIKE_OF_POST,
             File.getInstance ().LIKE_OF_POST_REGEX);
     if ( matcher==null ) return null;
     while (matcher.find ()){
         LikeOfPost likeOfPost = new LikeOfPost ();
         likeOfPost.setId (Integer.parseInt (matcher.group ("id")));
         likeOfPost.setUsername (matcher.group ("username"));
         likeOfPost.setPostId (Integer.parseInt (matcher.group ("postid")));
         likeOfPosts.add (likeOfPost);
     }
     return likeOfPosts;
 }

 public void close(){
     saveFile (likeOfPosts);
 }
 private void saveFile(List<LikeOfPost> likeOfPosts){
     StringBuilder stringBuilder = new StringBuilder ();
     likeOfPosts.forEach (likeOfPost -> stringBuilder.append (likeOfPost.ObjectToString ()));
     File.getInstance ().writeFile (stringBuilder,
             File.getInstance ().PATH_LIKE_OF_POST);
 }
}
