package be.bstorm.specification.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Player")
@Table(name = "player")
@Data
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastname;
    private String firstname;
}
