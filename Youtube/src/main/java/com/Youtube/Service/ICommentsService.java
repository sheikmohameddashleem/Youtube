package com.Youtube.Service;

import com.Youtube.Model.Comments;
import com.Youtube.Model.CommentsDto;

public interface ICommentsService {

	Comments unlike(Long id);

	Comments dislike(Long id);

	Comments like(Long id);

	Comments createComment(CommentsDto cd);

	String delete(Long userId, Long videoId, Long commentId);

}
