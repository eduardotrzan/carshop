package com.carshop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.List;

import com.carshop.domain.entity.enums.GarageStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "garage")
@ToString(callSuper = true, of = { "id", "status", "door" })
public class Garage extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garage_id_seq")
    @SequenceGenerator(name = "garage_id_seq", sequenceName = "garage_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 70)
    private GarageStatus status;

    @Column(name = "door", nullable = false)
    private Integer door;

    @OneToMany(mappedBy = "garage", fetch = FetchType.LAZY)
    private List<Appointment> appointments;
}
