package com.Youtube.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Youtube.Model.Comments;
import com.Youtube.Model.CommentsDto;
import com.Youtube.Service.ICommentsService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(value = "*")
public class CommentsController {

	private ICommentsService com;

	@PostMapping("/createComment")
	public ResponseEntity<Comments> create(@RequestBody CommentsDto c) {

		return new ResponseEntity<Comments>(com.createComment(c), HttpStatus.ACCEPTED);
	}

	@PatchMapping("/like/{id}")
	public ResponseEntity<Comments> Like(@PathVariable Long id) {

		return new ResponseEntity<Comments>(com.like(id), HttpStatus.ACCEPTED);
	}

	@PatchMapping("/Unlike/{id}")
	public ResponseEntity<Comments> UnLike(@PathVariable Long id) {

		return new ResponseEntity<Comments>(com.unlike(id), HttpStatus.ACCEPTED);
	}

	@PatchMapping("/dislike/{id}")
	public ResponseEntity<Comments> diaLike(@PathVariable Long id) {

		return new ResponseEntity<Comments>(com.dislike(id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{uid}/{vid}/{cid}")
	public ResponseEntity<String> delete(@PathVariable Long uid,@PathVariable Long vid,@PathVariable Long cid){
		return new ResponseEntity<String>(com.delete(uid,vid,cid), HttpStatus.ACCEPTED);
	
	}
}
