package org.example.filmratev2simplev.model;


import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mpa")
public class Mpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpa_id")
    private Long id;
    private String name;

}
