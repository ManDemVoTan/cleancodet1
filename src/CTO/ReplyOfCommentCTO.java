package CTO;

import model.CommentOfPost;
import model.ReplyOfComment;
import util.File;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ReplyOfCommentCTO {
    private static ReplyOfCommentCTO instance;
    public static ReplyOfCommentCTO getInstance(){
        if ( instance==null )instance = new ReplyOfCommentCTO ();
        return instance;
    }

    private List<ReplyOfComment> replyOfComments;
    public List<ReplyOfComment> getReplyOfComments(){
        if ( replyOfComments==null )replyOfComments = openFile ();
        return replyOfComments;
    }
    public List<ReplyOfComment> getByCommentId(int comment_id){
        List<ReplyOfComment> result = new ArrayList<> ();
      if ( replyOfComments==null )replyOfComments = openFile ();
        replyOfComments.forEach (replyOfComment -> {
            if ( replyOfComment.getCommentId ()==comment_id )result.add (replyOfComment);
        });
        return result;
    }
    public ReplyOfComment getById(int id){
        for (ReplyOfComment replyOfComment : replyOfComments){
            if ( replyOfComment.getId ()==id )return replyOfComment;
        }
        return null;
    }
    public ReplyOfComment add(ReplyOfComment replyOfComment){
        if ( replyOfComments==null )replyOfComments = openFile ();
        int maxId = 0;
        if(replyOfComments==null)replyOfComments = new ArrayList<> ();
            for (ReplyOfComment r : replyOfComments){
            if ( r.getId ()>maxId )maxId=r.getId ()+1;
        }
        replyOfComment.setId (maxId);
        replyOfComments.add (replyOfComment);
        return replyOfComment;
    }
    public void remove(ReplyOfComment replyOfComment){
        replyOfComments.remove (replyOfComment);
    }
    private List<ReplyOfComment> openFile(){
        List<ReplyOfComment> replyOfComments = new ArrayList<> ();
        Matcher matcher = File.getInstance ().getMacher ( File.getInstance ().PATH_REPLY_OF_COMMENT,
                File.getInstance ().REPLY_OF_COMMENT_REGEX);
        if ( matcher==null ) return null;
        while (matcher.find ()){
            ReplyOfComment replyOfComment = new ReplyOfComment ();
            replyOfComment.setId (Integer.parseInt (matcher.group ("id")));
            replyOfComment.setUsername (matcher.group ("username"));
            replyOfComment.setContent (matcher.group ("content"));
            replyOfComment.setCommentId (Integer.parseInt (matcher.group ("commentid")));
            replyOfComments.add (replyOfComment);
        }
        return replyOfComments;
    }
    public void close(){
        saveFile (replyOfComments);
    }
    private void saveFile(List<ReplyOfComment> replyOfComments){
        StringBuilder stringBuilder = new StringBuilder ();
        replyOfComments.forEach (replyOfComment -> stringBuilder.append (replyOfComment.ObjectToString ()));
        File.getInstance ().writeFile (stringBuilder,
                File.getInstance ().PATH_REPLY_OF_COMMENT);
    }
}
