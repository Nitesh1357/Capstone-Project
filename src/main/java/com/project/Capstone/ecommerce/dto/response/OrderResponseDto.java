package com.project.Capstone.ecommerce.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;

    @Schema(example = "Logitech Mouse")
    private String productName;

    private Double amount;
    private String status;
    private LocalDateTime createdAt;

    @Schema(example = "Nitesh Kumar")
    private String customerName;
}
