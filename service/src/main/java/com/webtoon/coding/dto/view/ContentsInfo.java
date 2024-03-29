package com.webtoon.coding.dto.view;

import com.webtoon.coding.domain.contents.Policy;

import java.util.Date;

public interface ContentsInfo {

    Long getId();

    String getName();

    String getAuthor();

    Policy getType();

    String getCoin();

    Date getOpenDate();

}
