package com.Youtube.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Youtube.Exception.YoutubeException;
import com.Youtube.Model.Channel;
import com.Youtube.Model.IsDeleted;
import com.Youtube.Model.PlayLists;
import com.Youtube.Model.User;
import com.Youtube.Model.UserDto;
import com.Youtube.Model.Video;
import com.Youtube.Repository.CommentsRepository;
import com.Youtube.Repository.PlayListRepository;
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
	
	@Autowired
	private PlayListRepository playListRepo;

	@Override
	public User registerUser(User user) throws YoutubeException {

		Optional<User> user1 = userRepo.findByusername(user.getUsername());

		if (!user1.isEmpty())
			throw new YoutubeException("User is Already Exist");

		return userRepo.save(user);
	}

	@Override
	public User addWatchList(Long id, Long userId) {
		Optional<User> user1 = userRepo.findById(userId);
		Optional<Video> video = videoRepo.findById(id);
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (video.isEmpty())
			throw new YoutubeException("No Video Found");

		User user2 = user1.get();
		List<Video> watchLater = user2.getWatchLater();
		watchLater.add(video.get());
		user2.setWatchLater(watchLater);
		return userRepo.save(user2);
	}


	@Override
	public User addHistory(Long id, Long userId) {
		Optional<User> user1 = userRepo.findById(userId);
		Optional<Video> video = videoRepo.findById(id);
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (video.isEmpty())
			throw new YoutubeException("No Video Found");

		User user2 = user1.get();
		List<Video> History = user2.getHistory();
		History.add(video.get());
		user2.setHistory(History);
		return userRepo.save(user2);
	}

	@Override
	public User addLikedVideos(Long id, Long userId) {

		Optional<User> user1 = userRepo.findById(userId);
		Optional<Video> video = videoRepo.findById(id);
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (video.isEmpty())
			throw new YoutubeException("No Video Found");

		User user2 = user1.get();
		List<Video> like = user2.getLikedVideos();
		Video vid = video.get();
		Long Liked = vid.getLikes() + 1;
		vid.setLikes(Liked);
		videoRepo.save(vid);
		like.add(vid);
		user2.setHistory(like);
		return userRepo.save(user2);

	}

	@Override
	public User subscribe(Long id1, Long id2) {

		Optional<User> subscribeTo = userRepo.findById(id1);
		Optional<User> subscriber = userRepo.findById(id2);
		if (subscribeTo.isEmpty())
			throw new YoutubeException("No User Found");
		if (subscriber.isEmpty())
			throw new YoutubeException("No Video Found");

		User subTo = subscribeTo.get();
		Long subscribers = subTo.getSubscribers() + 1;
		subTo.setSubscribers(subscribers);
		User saved = userRepo.save(subTo);

		User sub = subscriber.get();
		List<User> subscribed = sub.getSubscribedTo();
		subscribed.add(saved);

		return userRepo.save(sub);
	}

	@Override
	public User unsubscribe(Long id1, Long id2) {
		Optional<User> subscribeTo = userRepo.findById(id1);
		Optional<User> subscriber = userRepo.findById(id2);
		if (subscribeTo.isEmpty())
			throw new YoutubeException("No User Found");
		if (subscriber.isEmpty())
			throw new YoutubeException("No Video Found");

		User subTo = subscribeTo.get();
		Long subscribers = subTo.getSubscribers() - 1;
		subTo.setSubscribers(subscribers);
		User saved = userRepo.save(subTo);

		User sub = subscriber.get();
		List<User> subscribed = sub.getSubscribedTo();
		subscribed.remove(saved);

		return userRepo.save(sub);
	}

	@Override
	public User unlikeVid(Long id, Long userId) {
		Optional<User> user1 = userRepo.findById(userId);
		Optional<Video> video = videoRepo.findById(id);
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (video.isEmpty())
			throw new YoutubeException("No Video Found");

		User user2 = user1.get();
		List<Video> like = user2.getLikedVideos();
		Video vid = video.get();
		Long Liked = vid.getLikes() - 1;
		vid.setLikes(Liked);
		videoRepo.save(vid);
		like.remove(vid);
		user2.setLikedVideos(like);
		return userRepo.save(user2);
	}

	@Override
	public User clearHistory(Long userId) {
		Optional<User> user1 = userRepo.findById(userId);

		if (user1.isEmpty())
			throw new YoutubeException("No User Found");

		User user2 = user1.get();
		List<Video> History = user2.getHistory();
		History.clear();
		user2.setHistory(History);
		return userRepo.save(user2);
	}

	@Override
	public User deleteUser(Long userId) {
		Optional<User> user1 = userRepo.findById(userId);

		if (user1.isEmpty())
			throw new YoutubeException("No User Found");

		User user=user1.get();
		
		user.setIsdeleted(IsDeleted.YES);
		userRepo.save(user);
		return user;
	}

	@Override
	public User createPlayList(String name, Long userId) {
		Optional<User> user1 = userRepo.findById(userId);
		
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		
		PlayLists playList=new PlayLists();
		
		playList.setName(name);
		playList.setUser(user1.get());
		playList.setVideos(new ArrayList<>());
		
		PlayLists p=playListRepo.save(playList);
		User u=user1.get();
		List<PlayLists> pl=u.getPlayList();
		
		pl.add(p);
		u.setPlayList(pl);
		return userRepo.save(u);
	}

	@Override
	public User addPlayList(Long id, Long userId, Long idP) {
		Optional<User> user1 = userRepo.findById(userId);
		Optional<Video> video = videoRepo.findById(id);
		Optional<PlayLists> playL = playListRepo.findById(idP);
		
		
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (video.isEmpty())
			throw new YoutubeException("No Video Found");
		if (playL.isEmpty())
			throw new YoutubeException("No PlayList Found");
		User user2 = user1.get();
		List<PlayLists> playList = user2.getPlayList();
		
		for(PlayLists p:playList) {
			if(p.getId().equals(idP)) {
				List<Video> vids=p.getVideos();
				vids.add(video.get());
				p.setVideos(vids);
				playListRepo.save(p);
			}
		}
		user2.setPlayList(playList);
		return userRepo.save(user2);
	}

	@Override
	public User deletePlayList(Long userId, Long playListId) {
		Optional<User> user1 = userRepo.findById(userId);
		Optional<PlayLists> playL = playListRepo.findById(playListId);
		
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (playL.isEmpty())
			throw new YoutubeException("No PlayList Found");
		
	    User user=user1.get();
	    List<PlayLists> playList=user.getPlayList();
	    
	    playList.remove(playL);
	    
	    user.setPlayList(playList);
	    
	    return userRepo.save(user);
	}

	@Override
	public Channel channel(Long userId) {
		Optional<User> user1 = userRepo.findById(userId);
		
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		
		 User user=user1.get();
		
		Channel channel=new Channel();
		channel.setName(user.getName());
		channel.setPlayList(user.getPlayList());
		channel.setUpdloads(user.getVideos());
		
		return channel;
	}

	@Override
	public User disLike(Long id, Long userId) {
		Optional<User> user1 = userRepo.findById(userId);
		Optional<Video> video = videoRepo.findById(id);
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		if (video.isEmpty())
			throw new YoutubeException("No Video Found");

		User user2 = user1.get();
		Video vid = video.get();
		List<Video> like = user2.getLikedVideos();
		if(like.stream().anyMatch(a-> a.getVidId()==vid.getVidId())){
			Long Liked = vid.getLikes() - 1;
			Long disLike=vid.getDislikes()+1;
			vid.setLikes(Liked);
			vid.setDislikes(disLike);
			videoRepo.save(vid);
			like.remove(vid);
			user2.setLikedVideos(like);
		}
		    
		Long disLike=vid.getDislikes()+1;
		vid.setDislikes(disLike);
		videoRepo.save(vid);
		
		return userRepo.save(user2);
	}

	@Override
	public User editUser(UserDto user) {
		
		Optional<User> user1 = userRepo.findById(user.getId());
		
		if (user1.isEmpty())
			throw new YoutubeException("No User Found");
		
		User u=user1.get();
		
		u.setEmail(user.getEmail());
		u.setMobileNumber(user.getMobileNumber());
		u.setName(user.getName());
		u.setUsername(user.getUsername());
	    return userRepo.save(u);
	}

	


}
