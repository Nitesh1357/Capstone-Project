package com.project.Capstone.supportticket.dto.request;

import lombok.Data;

@Data
public class ReplyRequestDto {
    private String message;
    private String repliedBy; // "USER" or "ADMIN"
}
