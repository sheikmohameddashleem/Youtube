package com.Youtube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Youtube.Model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>, PagingAndSortingRepository<Video, Long> {

}
