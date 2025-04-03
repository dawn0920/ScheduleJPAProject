package org.example.schedulejpaproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;

    // 유저 식별자
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 일정 식별자
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }
}
