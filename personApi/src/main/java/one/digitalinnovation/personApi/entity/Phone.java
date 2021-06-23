package one.digitalinnovation.personApi.entity;
import lombok.*;
import one.digitalinnovation.personApi.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;
    @Column(nullable = false)
    private String number;

}