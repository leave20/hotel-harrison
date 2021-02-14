package com.hotelharrison.api.utils.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {
    private Long id;
    private String ruc;
    private String razonSocial;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creadoEn;
    private Double lateCheckOut;
    private String estado;
    private ReservaDto reserva;
}
