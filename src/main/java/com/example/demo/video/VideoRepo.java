package com.example.demo.video;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNullApi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource
@SecurityRequirement(name = "apiKey")
public interface VideoRepo extends CrudRepository<Video, String> {

//    @Override
//    Iterable<Video> findAll();

    @Override
    @PreAuthorize("@resourceAuth.checkResourceOwnership(#entity.user.username)")
    <S extends Video> S save(S entity);


    @Override
    @PreAuthorize("@resourceAuth.checkResourceOwnership(#entity.user.username)")
    void delete(Video entity);



}
