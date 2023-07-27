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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Youtube.Model.Video;
import com.Youtube.Model.VideoDto;
import com.Youtube.Service.VideoServices;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

	@Autowired
	private VideoServices videoservice;

	@PostMapping("/upload/{id}")
	private ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

		return new ResponseEntity<>(videoservice.uploadVideo(file, id), HttpStatus.CREATED);
	}

	@PutMapping("/EditVideo")
	private ResponseEntity<Video> editVideo(@RequestBody VideoDto vidDto) {

		return new ResponseEntity<>(videoservice.edit(vidDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteVideo/{id}")
	private ResponseEntity<String> deleted(@PathVariable Long id) {

		return new ResponseEntity<>(videoservice.delete(id), HttpStatus.OK);
	}
	
}
