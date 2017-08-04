package com.nmerris.weektwoproject.repositories;


import com.nmerris.weektwoproject.models.Applicant;
import org.springframework.data.repository.CrudRepository;

public interface ResumeRepository extends CrudRepository<Applicant, Long> {

    // can add cool custom methods here, ie
    // call this a 'query by method'
    // this is IT!!! no implementation necessary!! it automagically knows what to do
    // you MUST name your methods as it expects though: [defined start of method][your field][opt other defined stuff]
//    Iterable<Book> findAllByAuthor(String author);
//    Iterable<Book> findAllByAuthorLike(String partialAuthor);
    // etc

}

