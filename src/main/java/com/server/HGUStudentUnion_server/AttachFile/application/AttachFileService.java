package com.server.HGUStudentUnion_server.AttachFile.application;

import com.server.HGUStudentUnion_server.AttachFile.domain.repository.AttachFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttachFileService {

    @Autowired
    private AttachFileRepo attachFileRepo;

}
