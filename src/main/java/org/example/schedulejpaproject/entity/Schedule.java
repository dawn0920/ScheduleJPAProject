package org.example.schedulejpaproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Size(max = 4, message = "이름은 4글자 내로 작성해주세요. ")
    private String name;

    @Column(nullable = false)
    @Size(max = 10, message = "제목은 10글자 내로 작성해주세요. ")
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {}

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Schedule(String title, String contents, String name) {
        this.title = title;
        this.contents = contents;
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
