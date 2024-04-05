package com.example.demo.video;


import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @Column(length = 8)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long likes;
    @Column(length = 500)
    private String description;


    @Column(nullable = false)
    private Long views;

    @Column(nullable = false)
    private String minature;

    @ManyToOne()
    private User user;


//    public Video(Long id, String title, Long likes, String description, Long views, String minature, String user) {
//        this(id, title, likes, description, views, minature, (User) null);
//    }


}
