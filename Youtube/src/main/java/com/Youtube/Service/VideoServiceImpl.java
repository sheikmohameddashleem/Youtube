package com.Youtube.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Youtube.Exception.YoutubeException;
import com.Youtube.Model.User;
import com.Youtube.Model.Video;
import com.Youtube.Repository.UserRepository;
import com.Youtube.Repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoServices {

	@Autowired
	private AwsS3Service aws;

	@Autowired
	private VideoRepository videoRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public String uploadVideo(MultipartFile file, Long id) {
		String URL = aws.getUrl(file);

		Video video = new Video();

		video.setUrl(URL);

		videoRepo.save(video);
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty())
			throw new YoutubeException("No User Exists");
		User u = user.get();
		List<Video> vid = u.getVideos();

		vid.add(video);

		u.setVideos(vid);
		userRepo.save(u);
		return "VIDEO UPLOADED SUCCESSFULLY";
	}

}
