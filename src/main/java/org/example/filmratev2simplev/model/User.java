package org.example.filmratev2simplev.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")
@ToString(exclude = {"userFilm", "myFriends", "myFollowers"})
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String login;

//    @Pattern(regexp = "^(19|20)\\d{2}-\\d{2}-\\d{2}")
//    @DateTimeFormat
    @Column(nullable = false)
    private LocalDate birthday;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @CreationTimestamp(source = SourceType.DB)
    @Column(updatable = false)
    private LocalDateTime createdOn;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    private List<UserFilm> userFilm = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user1", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Friendship> myFriends = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user2", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Friendship> myFollowers = new ArrayList<>(); // ^_^

}
