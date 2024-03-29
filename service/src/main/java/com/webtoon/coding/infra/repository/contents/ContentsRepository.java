package com.webtoon.coding.infra.repository.contents;

import com.webtoon.coding.domain.contents.Contents;
import com.webtoon.coding.domain.contents.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long>, ContentsSupport {

    <T> Page<T> findByType(Pageable pageable, Policy type, Class<T> classType);

    <T> Page<T> findAllProjectedBy(Pageable pageable, Class<T> classType);

    <T> Optional<T> findById(Long id, Class<T> classType);

}
