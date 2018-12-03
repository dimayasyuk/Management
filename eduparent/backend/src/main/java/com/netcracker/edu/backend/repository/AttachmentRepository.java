package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.model.Attachments;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepository extends CrudRepository<Attachments,Long> {
    Iterable<Attachments> findAttachmentsByTaskId(Long id);
    Attachments findAttachmentsById(Long id);
}
