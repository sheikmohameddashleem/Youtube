package com.Youtube.Service;

import org.springframework.web.multipart.MultipartFile;

import com.Youtube.Model.Video;
import com.Youtube.Model.VideoDto;

public interface VideoServices {

	public String uploadVideo(MultipartFile file, Long id);

	public Video edit(VideoDto vidDto);

	public String delete(Long id);
}
