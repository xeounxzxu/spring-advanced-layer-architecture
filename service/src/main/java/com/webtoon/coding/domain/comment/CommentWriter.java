package com.webtoon.coding.domain.comment;

import com.webtoon.coding.domain.common.Writer;
import com.webtoon.coding.infra.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CommentWriter implements Writer<Comment> {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

}
