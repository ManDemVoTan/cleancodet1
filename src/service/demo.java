package service;

import CTO.*;
import model.*;
import util.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class demo {

    private String phuong = "Phuong";
    private String linh = "linh";
    private String dung = "dung";
    private String hoang = "hoang";

    public static void main(String[] args) {
        demo d = new demo();
//        d.createPost ();
        d.loadPost();
        d.timNguoiLikeDao();
    }

    public void createPost() {
//        Phương tạo ra post
        Post post = new Post();
        post.setUsername(phuong);
        post.setContent("Hello moi nguoi");
        post = PostCTO.getInstance().add(post);
        //Linh like post cuả phương
        LikeOfPost likeOfPost = new LikeOfPost();
        likeOfPost.setPostId(post.getId());
        likeOfPost.setUsername(linh);
        likeOfPost = LikeOfPostCTO.getInstance().add(likeOfPost);
        //Dung comment vao post cua phuong
        CommentOfPost commentOfPost = new CommentOfPost();
        commentOfPost.setPostId(post.getId());
        commentOfPost.setUsername(dung);
        commentOfPost.setContent("hi lai");
        commentOfPost = CommentOfPostCTO.getIntance().add(commentOfPost);
        //phuong like comment cua dung
        LikeOfComment likeOfComment = new LikeOfComment();
        likeOfComment.setCommentId(commentOfPost.getId());
        likeOfComment.setUsername(phuong);
        likeOfComment = LikeOfCommentCTO.getInstance().add(likeOfComment);
        //hoang rely cmt cua dung
        ReplyOfComment replyOfComment = new ReplyOfComment();
        replyOfComment.setCommentId(commentOfPost.getId());
        replyOfComment.setUsername(hoang);
        replyOfComment.setContent("hi lan thu 3");
        replyOfComment = ReplyOfCommentCTO.getInstance().add(replyOfComment);
        
        File.getInstance().closeAll();

    }

    public void loadPost() {
        //Phải gọi PostCTO.getInstance ().getPostList () vì chỉ có phương thức này là lấy
        // dữ liệu trong file
        for (Post p : PostCTO.getInstance().getPostList()) {
            System.out.println("--------POST--------\nid :" + p.getId());
            System.out.println(p.getUsername() + " : " + p.getContent());
            System.out.print(p.getLikeOfPost().size() + "Like do :");
            for (LikeOfPost l : p.getLikeOfPost()) {
                System.out.print(" - " + l.getUsername());
            }
            System.out.println();
            System.out.println("-------comment----------");
            //TƯơng tự với thằng này vì lúc lấy dữ liệu Post chưa lấy comment cho nó để tối ưu thời gian
            // khi nào cần (vd như lúc này ) thì mới lấy
            //Mấy thằn dưới tương tự
            for (CommentOfPost c : CommentOfPostCTO.getIntance().getByPostId(p.getId())) {
                System.out.println("**********" + c.getUsername() + " : " + c.getContent());
                System.out.println("********** " + LikeOfCommentCTO.getInstance().getByCommentId(c.getId()).size() + " Like");
                for (LikeOfComment lc : LikeOfCommentCTO.getInstance().getByCommentId(c.getId())) {
                    System.out.println(" + " + lc.getUsername());
                }
                System.out.println("**********------REPLY-------");

                for (ReplyOfComment rc : ReplyOfCommentCTO.getInstance().getByCommentId(c.getId())) {
                    System.out.println("**********" + rc.getUsername() + " : " + rc.getContent());
                }
            }
        }
    }

    public void maxLikeOfPost() {
        //tạo ra biến tạm để lưu số lượng like lớn nhất
        int maxLike = 0;
        //và ngu like nó
        Post postMaxLike = null;
        for (Post p : PostCTO.getInstance().getPostList()) {
            //getLikeOfPost ().size () là lấy về size (số lượng) của list LikeOfPost đấy
            if (p.getLikeOfPost().size() > maxLike) {
                //Nếu nó lớn hơn max thì cho nó bằng max và cho postMax = post này  luôn
                maxLike = p.getLikeOfPost().size();
                postMaxLike = p;
            }
        }
        //Mấy thằng duwosci tương tự
        System.out.println("Post co nhieu like nhat la :" + postMaxLike + "voi " + maxLike + " like");

    }

    public void maxComtOfPost() {

        int maxCmt = 0;

        Post postMaxCmt = null;
        for (Post p : PostCTO.getInstance().getPostList()) {
            if (p.getCommentOfPost().size() > maxCmt) {
                maxCmt = p.getCommentOfPost().size();
                postMaxCmt = p;
            }
        }
        System.out.println("Post co nhieu cmt nhat la :" + postMaxCmt + "voi " + maxCmt + " like");
    }

    public void maxLikeOfCmt() {

        int maxLike = 0;

        CommentOfPost COPmaxLike = null;
        //lặp hết tất cả các post
        for (Post p : PostCTO.getInstance().getPostList()) {
            //lặp luôn cả cmt từng post
            for (CommentOfPost c : p.getCommentOfPost()) {
                if (c.getLikeOfComment().size() > maxLike) {
                    maxLike = c.getLikeOfComment().size();
                    COPmaxLike = c;
                }
            }
        }
        System.out.println("CMT duoc nhieu like nhat la :" + COPmaxLike + " voi " + maxLike + "Like");
    }

    public void maxReplyOfCmt() {
        int maxReply = 0;
        CommentOfPost COPmaxReply = null;
        for (Post p : PostCTO.getInstance().getPostList()) {
            for (CommentOfPost c : p.getCommentOfPost()) {
                if (c.getReplyOfComment().size() > maxReply) {
                    maxReply = c.getReplyOfComment().size();
                    COPmaxReply = c;
                }
            }
        }
        System.out.println("CMT duoc nhieu reply nhat la :" + COPmaxReply + " voi " + maxReply + "Like");
    }

    public void timNguoiLikeDao() {
        Map<String, Integer> mapLikeDai = new HashMap<>();
        CommentOfPost COPmaxReply = null;
        for (Post p : PostCTO.getInstance().getPostList()) {
            //Nếu trong map chưa tồn tại user này thì thêm vào
            //Với key là username và số like = 1;
            if (mapLikeDai.get(p.getUsername()) == null) {
                mapLikeDai.put(p.getUsername(), 1);
            } //Nếu đã tồn tại thì tăng số like của người đó lên 1
            else {
                mapLikeDai.put(p.getUsername(), mapLikeDai.get(p.getUsername()) + 1);
            }
            for (CommentOfPost c : p.getCommentOfPost()) {

                if (mapLikeDai.get(c.getUsername()) == null) {
                    mapLikeDai.put(c.getUsername(), 1);
                } //Nếu đã tồn tại thì tăng số like của người đó lên 1
                else {
                    mapLikeDai.put(c.getUsername(), mapLikeDai.get(c.getUsername()) + 1);
                }
                //Đếm cả like của cmt luôn
            }
        }
        //Sau vòng lặp ta đã có 1 danh sách các username với số lượng like của ng đó
        int maxLikeDao = 0;
        String userLikeDao = "";
        // .keySet() để lấy dánh sách các key( ở đây là các usernama ta đã lưu vào (put))
        for (String s : mapLikeDai.keySet()) {
            if (mapLikeDai.get(s) > maxLikeDao) {
               
                maxLikeDao = mapLikeDai.get(s);
                userLikeDao = s;
            }
        }
       
        List<String> listUserLikeDao = new ArrayList<>();
        for (String s : mapLikeDai.keySet()) {
            if (mapLikeDai.get(s) == maxLikeDao) {
                listUserLikeDao.add(s);
            }
        }
        System.out.println("User like dao nhieu nhat la " + userLikeDao + " voi " + maxLikeDao + " Lan Like");
        System.out.println("------------------------");
        if (listUserLikeDao.size() > 1) {
            System.out.println("Danh sách top 1 like dao : ");
            listUserLikeDao.forEach(s -> System.out.printf("|" + s + "|"));
        } else {
            System.out.println("Khong co truong hop nhieu hon 1 thang top 1 like dao");
        }
    }
}
