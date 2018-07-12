package CTO;

import model.LikeOfComment;
import model.LikeOfPost;
import util.File;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class LikeOfCommentCTO {
    private static LikeOfCommentCTO instance;
    public static LikeOfCommentCTO getInstance(){
        if ( instance==null )instance = new LikeOfCommentCTO ();
        return instance;
    }

    private List<LikeOfComment> likeOfComments;
    public List<LikeOfComment> getLikeOfComments(){
        if ( likeOfComments==null )likeOfComments = openFile ();
        return likeOfComments;
    }
    public List<LikeOfComment> getByCommentId(int id){
        List<LikeOfComment> result = new ArrayList<> ();
       if ( likeOfComments==null ) likeOfComments = openFile ();
        likeOfComments.forEach (likeOfComment -> {
            if ( likeOfComment.getCommentId ()==id )result.add (likeOfComment);
        });
        return result;
    }
    public LikeOfComment getById(int id){
        for (LikeOfComment likeOfComment : likeOfComments){
            if ( likeOfComment.getId ()==id )return likeOfComment;
        }
        return null;
    }
    public LikeOfComment add(LikeOfComment likeOfComment){
        if ( likeOfComments==null )likeOfComments =openFile ();
        int maxId = 0;
     if ( likeOfComments==null )likeOfComments=new ArrayList<> ();
         for (LikeOfComment l : likeOfComments){
            if ( l.getId ()>maxId )maxId= l.getId ()+1;
        }
        likeOfComment.setId (maxId);
        likeOfComments.add (likeOfComment);
        return likeOfComment;
    }
    public void remove(LikeOfComment likeOfComment){
        likeOfComments.remove (likeOfComment);
    }
    private List<LikeOfComment> openFile(){
        List<LikeOfComment> likeOfComments = new ArrayList<> ();
        Matcher matcher = File.getInstance ().getMacher ( File.getInstance ().PATH_LIKE_OF_COMMENT,
                File.getInstance ().LIKE_OF_COMMENT_REGEX);
        if ( matcher==null ) return null;
        while (matcher.find ()){
            LikeOfComment likeOfComment = new LikeOfComment ();
            likeOfComment.setId (Integer.parseInt (matcher.group ("id")));
            likeOfComment.setUsername (matcher.group ("username"));
            likeOfComment.setCommentId (Integer.parseInt (matcher.group ("commentid")));
            likeOfComments.add (likeOfComment);
        }

        return likeOfComments;
    }

    public void close(){
        saveFile (likeOfComments);
    }
    private void saveFile(List<LikeOfComment> likeOfComments){
        StringBuilder stringBuilder = new StringBuilder ();
        likeOfComments.forEach (likeOfComment  -> stringBuilder.append (likeOfComment.ObjectToString ()));
        File.getInstance ().writeFile (stringBuilder,
                File.getInstance ().PATH_LIKE_OF_COMMENT);
    }
}
