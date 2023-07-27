package com.Youtube.Service;

import org.springframework.web.multipart.MultipartFile;

public interface AWS {

	public String getUrl(MultipartFile file);
}
