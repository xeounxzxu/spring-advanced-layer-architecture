package com.webtoon.coding.service.comment;

import com.webtoon.coding.domain.comment.Comment;
import com.webtoon.coding.domain.comment.CommentVerifier;
import com.webtoon.coding.domain.content.Contents;
import com.webtoon.coding.domain.core.Reader;
import com.webtoon.coding.domain.user.User;
import com.webtoon.coding.dto.model.comment.ContentsComment;
import com.webtoon.coding.dto.request.ContentsCommentRequest;
import com.webtoon.coding.infra.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentVerifier verifier;

    private final Reader<Contents> contentReader;

    private final Reader<User> userReader;

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment created(ContentsCommentRequest dto) {

        Contents contents = contentReader.get(dto.getContentsId());

        User user = userReader.get(dto.getUserId());

        // 컨텐츠 코맨트 객체 생성
        ContentsComment contentsComment = ContentsComment.of(dto.getComment(), dto.getType(), user, contents);

        // 컨텐츠 코멘트 객체는 생성을한다.
        Comment entity = contentsComment.created(verifier);

        return commentRepository.save(entity);
    }
}