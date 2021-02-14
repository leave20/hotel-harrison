package com.hotelharrison.api.utils.other;

import com.hotelharrison.api.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoDto {
    private Long id;
    private Integer cantidad;
    private Double total;
    private ReservaDto reserva ;
    private Producto producto;
}
