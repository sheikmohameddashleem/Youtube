package com.Youtube.Service;

import com.Youtube.Exception.YoutubeException;
import com.Youtube.Model.User;

public interface IUserService {

	User registerUser(User user) throws YoutubeException;

	User addWatchList(Long id, Long userId);

	User addPlayList(Long id, Long userId);

	User addHistory(Long id, Long userId);

	User addLikedVideos(Long id, Long userId);

	User subscribe(Long id1, Long id2);

	User unsubscribe(Long id1, Long id2);

	User unlikeVid(Long id, Long userId);

	User clearHistory(Long userId);

}
