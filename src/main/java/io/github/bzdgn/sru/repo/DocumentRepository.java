package io.github.bzdgn.sru.repo;

import io.github.bzdgn.sru.entity.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<FileMeta, Long> {
}
