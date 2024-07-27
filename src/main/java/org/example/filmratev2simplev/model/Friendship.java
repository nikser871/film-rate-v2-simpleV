package org.example.filmratev2simplev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "friendship")
public class Friendship {

    @EmbeddedId
    private Id id;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Id {

        @Column(name = "user_id1", nullable = false)
        private Long friendId1;

        @Column(name = "user_id2", nullable = false)
        private Long friendId2;
    }

    @ManyToOne
    @JoinColumn(name = "user_id1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id2")
    private User user2;

    public void add(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.id = new Id(user1.getId(), user2.getId());

    }


}
