package com.Youtube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Youtube.Model.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long>, PagingAndSortingRepository<Comments, Long> {

}
