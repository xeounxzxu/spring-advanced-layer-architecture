package com.lezhin.coding.domain;

import com.lezhin.coding.config.exption.DomainException;
import com.lezhin.coding.constants.ContentsType;
import com.lezhin.coding.domain.content.Contents;
import com.lezhin.coding.mock.ContentsMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentsTest {

  @Test
  @DisplayName("free 타입으로 변경")
  void changedFreeType() {

    Contents mock = ContentsMock.createdMock();

    mock.changedFreeType();

    Assertions.assertEquals(ContentsType.FREE, mock.getType());
  }

  @Test
  @DisplayName("유로 타입으로 변경")
  void changedPagar() {
    Contents mock = ContentsMock.createdMock();

    mock.changedPagar();

    Assertions.assertEquals(ContentsType.PAGAR, mock.getType());
  }

  @Test
  @DisplayName("free 타입 테스트 케이스")
  void checkedTypeAndCoin_freeType() {

    Contents mock = ContentsMock.createdMock();

    mock.checkedTypeAndCoin();

    Assertions.assertEquals("0", mock.getCoin());
  }

  @Test
  @DisplayName("유료 타입 테스트 케이스")
  void checkedTypeAndCoin_pagarType() {

    Contents mock =
        Contents.builder()
            .id(1L)
            .name("test1")
            .author("test1")
            .type(ContentsType.PAGAR)
            .coin("100")
            .build();

    mock.checkedTypeAndCoin();

    Assertions.assertEquals("100", mock.getCoin());
  }

  @Test
  @DisplayName("유료 타입 이하 테스트 케이스")
  void checkedTypeAndCoin_이하() {

    Contents mock =
        Contents.builder()
            .id(1L)
            .name("test1")
            .author("test1")
            .type(ContentsType.PAGAR)
            .coin("99")
            .build();

    Assertions.assertThrows(DomainException.class, () -> mock.checkedTypeAndCoin());
  }

  @Test
  @DisplayName("유료 타입 초과 테스트 케이스")
  void checkedTypeAndCoin_초과() {

    Contents mock =
        Contents.builder()
            .id(1L)
            .name("test1")
            .author("test1")
            .type(ContentsType.PAGAR)
            .coin("501")
            .build();

    Assertions.assertThrows(DomainException.class, () -> mock.checkedTypeAndCoin());
  }
}
