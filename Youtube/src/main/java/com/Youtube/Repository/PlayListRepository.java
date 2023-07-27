package com.Youtube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Youtube.Model.Comments;
import com.Youtube.Model.PlayLists;

public interface PlayListRepository extends JpaRepository<PlayLists, Long>, PagingAndSortingRepository<PlayLists, Long> {

}
