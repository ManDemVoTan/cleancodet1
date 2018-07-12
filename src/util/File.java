package util;

import CTO.*;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File {
    public final String PATH_POST = "post.txt";
    public final String PATH_LIKE_OF_POST = "likeofpost.txt";
    public final String PATH_COMMENT_OF_POST = "commentofpost.txt";
    public final String PATH_LIKE_OF_COMMENT = "likeofcomment.txt";
    public final String PATH_REPLY_OF_COMMENT = "replyofcomment.txt";
    public final String POST_REGEX =             "id : (?<id>.*)\nusername : (?<username>\\w.*)\ncontent : (?<content>\\w.*)";
    public final String LIKE_OF_POST_REGEX =     "id : (?<id>.*)\nusername : (?<username>\\w.*)\npost_id : (?<postid>.*)";
    public final String COMMENT_OF_POST_REGEX =  "id : (?<id>.*)\nusername : (?<username>\\w.*)\ncontent : (?<content>\\w.*)\npost_id : (?<postid>.*)";
    public final String LIKE_OF_COMMENT_REGEX =  "id : (?<id>.*)\nusername : (?<username>\\w.*)\ncomment_id : (?<commentid>.*)";
    public final String REPLY_OF_COMMENT_REGEX = "id : (?<id>.*)\nusername : (?<username>\\w.*)\ncontent : (?<content>\\w.*)\ncomment_id : (?<commentid>.*)";

    private static   File instance ;
    //Sử dụng SingleTon để gọi
    public static File getInstance(){
        if ( instance==null )instance = new File ();
        return instance;
    }

    public Matcher getMacher(String path,String regex) {
       try {
           StringBuilder stringBuilder = new StringBuilder ();
           BufferedReader bufferedReader =
                   new BufferedReader (new FileReader (path));
           String read = null;
           while ((read = bufferedReader.readLine ())!=null){
               stringBuilder.append (read);
               stringBuilder.append ("\n");
           }
           Pattern pattern = Pattern
                   .compile (regex);
           Matcher matcher = pattern.matcher (stringBuilder);
           return matcher;
       }catch (IOException e){
           return null;
       }
    }
    public void writeFile(StringBuilder str, String path){
       try {
           BufferedWriter bufferedWriter =
                   new BufferedWriter (new FileWriter (path));
           bufferedWriter.write (String.valueOf (str));
           bufferedWriter.close ();
       }catch (IOException e){

       }
    }
    public void closeAll(){
        PostCTO.getInstance ().close ();
        LikeOfPostCTO.getInstance ().close ();
        CommentOfPostCTO.getIntance ().close ();
        LikeOfCommentCTO.getInstance ().close ();
        ReplyOfCommentCTO.getInstance ().close ();
    }

}
