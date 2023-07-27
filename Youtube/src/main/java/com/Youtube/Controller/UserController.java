package com.Youtube.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Youtube.Model.User;
import com.Youtube.Service.ICommentsService;
import com.Youtube.Service.IUserService;
import com.Youtube.Service.VideoServices;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ICommentsService commentService;

	@Autowired
	private VideoServices videoService;

	@PostMapping("/Register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {

		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/WatchListAdd/{id1}/{id2}")
	public ResponseEntity<User> watchList(@PathVariable Long id, @PathVariable Long userId) {

		return new ResponseEntity<>(userService.addWatchList(id, userId), HttpStatus.ACCEPTED);
	}

	@PutMapping("/PlayListAdd/{id1}/{id2}")
	public ResponseEntity<User> playList(@PathVariable Long id, @PathVariable Long userId) {

		return new ResponseEntity<>(userService.addPlayList(id, userId), HttpStatus.ACCEPTED);
	}

	@PutMapping("/HistoryAdd/{id1}/{id2}")
	public ResponseEntity<User> history(@PathVariable Long id, @PathVariable Long userId) {

		return new ResponseEntity<>(userService.addHistory(id, userId), HttpStatus.ACCEPTED);
	}
	@PutMapping("/likedVid/{id1}/{id2}")
	public ResponseEntity<User> likedVideos(@PathVariable Long id, @PathVariable Long userId) {

		return new ResponseEntity<>(userService.addLikedVideos(id, userId), HttpStatus.ACCEPTED);
	}
	@PutMapping("/unlike/{id1}/{id2}")
	public ResponseEntity<User> unlikedVideos(@PathVariable Long id, @PathVariable Long userId) {

		return new ResponseEntity<>(userService.unlikeVid(id, userId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/subscribe/{id1}/{id2}")
	public ResponseEntity<User> subscribed(@PathVariable Long id1, @PathVariable Long id2) {

		return new ResponseEntity<>(userService.subscribe(id1, id2), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/unsubscribe/{id1}/{id2}")
	public ResponseEntity<User> unsubscribed(@PathVariable Long id1, @PathVariable Long id2) {

		return new ResponseEntity<>(userService.unsubscribe(id1, id2), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/cleanHistory/{id}")
	public ResponseEntity<User> clearhistory(@PathVariable Long userId) {

		return new ResponseEntity<>(userService.clearHistory(userId), HttpStatus.ACCEPTED);
	}
	
	
}
