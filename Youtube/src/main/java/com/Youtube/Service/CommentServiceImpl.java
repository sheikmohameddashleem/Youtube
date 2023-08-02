package com.Youtube.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Youtube.Exception.YoutubeException;
import com.Youtube.Model.Comments;
import com.Youtube.Model.CommentsDto;
import com.Youtube.Model.User;
import com.Youtube.Model.Video;
import com.Youtube.Repository.CommentsRepository;
import com.Youtube.Repository.UserRepository;
import com.Youtube.Repository.VideoRepository;

@Service
public class CommentServiceImpl implements ICommentsService {

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private VideoRepository vr;
	
	@Autowired
	private CommentsRepository cr;
	
	@Override
	public Comments createComment(CommentsDto cd) {
		Optional<Video> vid=vr.findById((long) cd.getVideoId());
		Optional<User> uid=ur.findById((long) cd.getUserId());
		
		if(vid.isEmpty())throw new YoutubeException("No video found");
		if(uid.isEmpty())throw new YoutubeException("No user found");
		
		Comments comments=new Comments();
		User u=uid.get();
		comments.setUser(u);
		comments.setVideo(vid.get());
		comments.setDescription(cd.getComments());
		LocalDateTime ldt=LocalDateTime.now();
		comments.setDateAndTime(ldt);
		
		
		return cr.save(comments);
	}
	
	@Override
	public Comments like(Long id) {
		Optional<Comments> com = cr.findById(id);
		if (com.isEmpty())
			throw new YoutubeException("No comment Found");
		Comments coment=com.get();
		coment.setLikes(coment.getLikes()+1);
		return cr.save(coment);
	}
	@Override
	public Comments dislike(Long id) {
		Optional<Comments> com = cr.findById(id);
		if (com.isEmpty())
			throw new YoutubeException("No comment Found");
		Comments coment=com.get();
		coment.setLikes(coment.getDislikes()+1);
		return cr.save(coment);
	}
	@Override
	public Comments unlike(Long id) {
		Optional<Comments> com = cr.findById(id);
		if (com.isEmpty())
			throw new YoutubeException("No comment Found");
		Comments coment=com.get();
		coment.setLikes(coment.getLikes()-1);
		return cr.save(coment);
	}
	
	@Override
	public String delete(Long userId, Long videoId,Long commentId) {
		Optional<Video> vid=vr.findById( videoId);
		Optional<User> uid=ur.findById(userId);
		Optional<Comments> com = cr.findById(commentId);
		

		if(vid.isEmpty())throw new YoutubeException("No video found");
		if(uid.isEmpty())throw new YoutubeException("No user found");
		if (com.isEmpty())throw new YoutubeException("No comment Found");
			
		
		if(uid.get().getId()==com.get().getUser().getId() || vid.get().getUser().getId()==com.get().getUser().getId()) {
			cr.delete(com.get());
		}
		
		return "Deleted";
	}
	
}
