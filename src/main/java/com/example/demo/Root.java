package com.example.demo;


import com.example.demo.form.VideoForm;
import com.example.demo.hooks.FileExtension;
import com.example.demo.user.UserRepo;
import com.example.demo.video.Video;
import com.example.demo.video.VideoRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class Root {
    @PostMapping("/")
    public String hello() {
        return "hello world";
    }

    private final VideoRepo videoRepo;

    private final UserRepo userRepo;

    @Autowired
    public Root(VideoRepo videoRepo, UserRepo userRepo) {
        this.videoRepo = videoRepo;
        this.userRepo = userRepo;
    }


    @PostMapping(value = "/eee", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String dupa(@RequestPart(name = "video") VideoForm videoForm,
                       @RequestPart MultipartFile file,
                       @RequestPart MultipartFile minature
    ) throws BadRequestException {
//        try {

        String file_1 = FileExtension.get(file.getOriginalFilename());
        String file_2 = FileExtension.get(minature.getOriginalFilename());

        if (file_1 == null || file_2 == null)
            throw new BadRequestException("Bad file extension");

        if (!file_1.equals("mp4") || !file_2.matches("jpg|jpeg|png"))
            throw new BadRequestException("Bad file extension");

        try {

            final String mp4_id = RandomStringUtils.randomAlphanumeric(8);
            final String minature_id = RandomStringUtils.randomAlphanumeric(12);
            final String path = "/Users/trytytus/Downloads/demo/src/main/resources/public/%s.%s";

            File transferFile = new File(String.format(path, mp4_id, file_1));
            File transferMinature = new File(String.format(path, minature_id, file_2));

            file.transferTo(transferFile);
            minature.transferTo(transferMinature);

            Video video = new Video();

            video.setId(mp4_id);
            video.setDescription(videoForm.getDescription());
            video.setLikes(0L);
            video.setMinature(minature_id + "." + file_2);
            video.setTitle(videoForm.getTitle());
            video.setViews(0L);
            video.setUser(userRepo.findByUsername(videoForm.getUser()));

            videoRepo.save(video);

            return "Success";
        } catch (IOException e)
        {
            throw new BadRequestException();
        }
    }



}
