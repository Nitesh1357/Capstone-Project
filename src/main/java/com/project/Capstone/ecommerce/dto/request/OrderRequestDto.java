package com.project.Capstone.ecommerce.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderRequestDto {
    @Schema(example = "1")
    private Long customerId;

    @Schema(example = "Logitech Mouse")
    private String productName;

    @Schema(example = "1499.99")
    private Double amount;
}
