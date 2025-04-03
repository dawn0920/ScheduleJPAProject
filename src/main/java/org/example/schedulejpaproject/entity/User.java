package org.example.schedulejpaproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
// 모든 필드를 매개변수로 받는 생성자를 자동으로 생성 (모든 필드이기 때문에 내가 아래 추가한 생성자는 따로 추가 되지 않는다)
@NoArgsConstructor // 매개변수가 없는 기본 생성자
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
