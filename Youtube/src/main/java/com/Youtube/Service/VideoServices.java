package com.Youtube.Service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoServices {

	public String uploadVideo(MultipartFile file, Long id);
}
