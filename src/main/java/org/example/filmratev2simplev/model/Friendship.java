package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"user1", "user2"})
@Entity
@Table(name = "friendship")
public class Friendship {

    @EmbeddedId
    private Id id = new Id();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "user_id1")
        private Long friendId1;

        @Column(name = "user_id2")
        private Long friendId2;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id1", insertable=false, updatable=false)
    private User user1;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id2", insertable=false, updatable=false)
    private User user2;

    public void add(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.id.setFriendId1(user1.getId());
        this.id.setFriendId2(user2.getId());
        user1.getMyFriends().add(this);
        user2.getMyFollowers().add(this);

    }


}
