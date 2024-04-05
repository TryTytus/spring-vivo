package com.example.demo.comment;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
@SecurityRequirement(name = "apiKey")
public interface CommentRepo extends CrudRepository<Comment, Long> {


    @Override
    @PreAuthorize("@resourceAuth.checkResourceOwnership(#entity.user.username)")
    <S extends Comment> S save(S entity);

    @Override
    @PreAuthorize("@resourceAuth.checkResourceOwnership(#entity.user.username)")
    void delete(Comment entity);
}
