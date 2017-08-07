package com.nmerris.weektwoproject.repositories;

import com.nmerris.weektwoproject.models.Resume;
import org.springframework.data.repository.CrudRepository;

// no custom methods necessary at this point
public interface ResumeRepository extends CrudRepository<Resume, Long> {


}

