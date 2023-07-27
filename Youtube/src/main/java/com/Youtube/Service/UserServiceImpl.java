package com.Youtube.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Youtube.Exception.YoutubeException;
import com.Youtube.Model.User;
import com.Youtube.Model.Video;
import com.Youtube.Repository.CommentsRepository;
import com.Youtube.Repository.UserRepository;
import com.Youtube.Repository.VideoRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private VideoRepository videoRepo;
	
	@Autowired
	private CommentsRepository commentsRepo;
	
	@Override
	public User registerUser(User user) throws YoutubeException {
		
	
		Optional<User> user1=userRepo.findByusername(user.getUsername());
		
		if(!user1.isEmpty())throw new YoutubeException("User is Already Exist");
		
		return userRepo.save(user);
	}

	
	@Override
	public User addWatchList(Long id, Long userId) {
		Optional<User> user1=userRepo.findById(userId);
		Optional<Video> video=videoRepo.findById(id);
		if(user1.isEmpty())throw new YoutubeException("No User Found");
		if(video.isEmpty())throw new YoutubeException("No Video Found");
		
		User user2=user1.get();
		List<Video> watchLater=user2.getWatchLater();
		watchLater.add(video.get());
		user2.setWatchLater(watchLater);
		return userRepo.save(user2);
	}


	@Override
	public User addPlayList(Long id, Long userId) {
		Optional<User> user1=userRepo.findById(userId);
		Optional<Video> video=videoRepo.findById(id);
		if(user1.isEmpty())throw new YoutubeException("No User Found");
		if(video.isEmpty())throw new YoutubeException("No Video Found");
		
		User user2=user1.get();
		List<Video> playList=user2.getPlayList();
		playList.add(video.get());
		user2.setPlayList(playList);
		return userRepo.save(user2);
	}


	@Override
	public User addHistory(Long id, Long userId) {
		Optional<User> user1=userRepo.findById(userId);
		Optional<Video> video=videoRepo.findById(id);
		if(user1.isEmpty())throw new YoutubeException("No User Found");
		if(video.isEmpty())throw new YoutubeException("No Video Found");
		
		User user2=user1.get();
		List<Video> History=user2.getHistory();
		History.add(video.get());
		user2.setHistory(History);
		return userRepo.save(user2);
	}


	@Override
	public User addLikedVideos(Long id, Long userId) {
		
		Optional<User> user1=userRepo.findById(userId);
		Optional<Video> video=videoRepo.findById(id);
		if(user1.isEmpty())throw new YoutubeException("No User Found");
		if(video.isEmpty())throw new YoutubeException("No Video Found");
		
	
		User user2=user1.get();
		List<Video> like=user2.getLikedVideos();
		Video vid=video.get();
		Long Liked=vid.getLikes()+1;
		vid.setLikes(Liked);
		videoRepo.save(vid);
		like.add(vid);
		user2.setHistory(like);
		return userRepo.save(user2);
		
	}


	@Override
	public User subscribe(Long id1, Long id2) {
		
		Optional<User> subscribeTo=userRepo.findById(id1);
		Optional<User> subscriber=userRepo.findById(id2);
		if(subscribeTo.isEmpty())throw new YoutubeException("No User Found");
		if(subscriber.isEmpty())throw new YoutubeException("No Video Found");
		
		User subTo=subscribeTo.get();
		Long subscribers=subTo.getSubscribers()+1;
		subTo.setSubscribers(subscribers);
		User saved=userRepo.save(subTo);
		
		User sub=subscriber.get();
		List<User> subscribed=sub.getSubscribedTo();
		subscribed.add(saved);
		
		return userRepo.save(sub);
	}


	@Override
	public User unsubscribe(Long id1, Long id2) {
		Optional<User> subscribeTo=userRepo.findById(id1);
		Optional<User> subscriber=userRepo.findById(id2);
		if(subscribeTo.isEmpty())throw new YoutubeException("No User Found");
		if(subscriber.isEmpty())throw new YoutubeException("No Video Found");
		
		User subTo=subscribeTo.get();
		Long subscribers=subTo.getSubscribers()-1;
		subTo.setSubscribers(subscribers);
		User saved=userRepo.save(subTo);
		
		User sub=subscriber.get();
		List<User> subscribed=sub.getSubscribedTo();
		subscribed.remove(saved);
		
		return userRepo.save(sub);
	}


	@Override
	public User unlikeVid(Long id, Long userId) {
		Optional<User> user1=userRepo.findById(userId);
		Optional<Video> video=videoRepo.findById(id);
		if(user1.isEmpty())throw new YoutubeException("No User Found");
		if(video.isEmpty())throw new YoutubeException("No Video Found");
		
	
		User user2=user1.get();
		List<Video> like=user2.getLikedVideos();
		Video vid=video.get();
		Long Liked=vid.getLikes()-1;
		vid.setLikes(Liked);
		videoRepo.save(vid);
		like.remove(vid);
		user2.setHistory(like);
		return userRepo.save(user2);
	}


	@Override
	public User clearHistory(Long userId) {
		Optional<User> user1=userRepo.findById(userId);
		
		if(user1.isEmpty())throw new YoutubeException("No User Found");

		User user2=user1.get();
		List<Video> History=user2.getHistory();
		History.clear();
		user2.setHistory(History);
		return userRepo.save(user2);
	}
	
	
	
	

}
