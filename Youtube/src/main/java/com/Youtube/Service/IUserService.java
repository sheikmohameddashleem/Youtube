package com.Youtube.Service;

import com.Youtube.Exception.YoutubeException;
import com.Youtube.Model.Channel;
import com.Youtube.Model.User;
import com.Youtube.Model.UserDto;

public interface IUserService {

	User registerUser(User user) throws YoutubeException;

	User addWatchList(Long id, Long userId);

	User addPlayList(Long id, Long userId, Long idP);

	User addHistory(Long id, Long userId);

	User addLikedVideos(Long id, Long userId);

	User subscribe(Long id1, Long id2);

	User unsubscribe(Long id1, Long id2);

	User unlikeVid(Long id, Long userId);

	User clearHistory(Long userId);

	User deleteUser(Long userId);

	User createPlayList(String name, Long userId);

	User deletePlayList(Long userId, Long playListId);

	Channel channel(Long userId);

	User disLike(Long id, Long userId);

	User editUser(UserDto user);

}
