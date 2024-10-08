package com.acs.acs.Enitities;

import com.acs.acs.ENUM.BoxType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "wh_box_label")
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseBoxLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long warehouseId;
    @Enumerated(EnumType.STRING)
    private BoxType boxType;
    private Boolean boxStatus;
}
