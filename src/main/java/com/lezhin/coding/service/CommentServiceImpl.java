package com.lezhin.coding.service;

import com.lezhin.coding.config.exption.NoDataException;
import com.lezhin.coding.constants.MsgType;
import com.lezhin.coding.domain.content.Comment;
import com.lezhin.coding.domain.content.Contents;
import com.lezhin.coding.domain.user.User;
import com.lezhin.coding.repository.CommentRepository;
import com.lezhin.coding.repository.ContentsRepository;
import com.lezhin.coding.repository.UserRepository;
import com.lezhin.coding.service.dto.CommentStoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  private final UserRepository userRepository;

  private final ContentsRepository contentsRepository;

  @Override
  @Transactional
  public Comment createdComment(CommentStoreDTO dto) {

    final User user =
        userRepository
            .findById(dto.getUserId())
            .orElseThrow(() -> new NoDataException(MsgType.NoUserData));

    final Contents contents =
        contentsRepository
            .findById(dto.getContentsId())
            .orElseThrow(() -> new NoDataException(MsgType.NoContentsData));

    return commentRepository.save(dto.toEntity(user, contents));
  }
}
