package com.lezhin.coding.domain;

import com.lezhin.coding.config.exption.DomainException;
import com.lezhin.coding.domain.content.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

  @Test
  @DisplayName("특수 기호 미포함 테스트 케이스")
  void checkedComment_notInclude() {
    Comment mock = Comment.builder().comment("hello").build();
    mock.checkedComment();
  }

  @Test
  @DisplayName("특수 기호 포함 테스트 케이스")
  void checkedComment_include() {
    Comment mock = Comment.builder().comment("hello^^").build();
    Assertions.assertThrows(DomainException.class, () -> mock.checkedComment());
  }
}
