package com.gurpreet.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "Api Path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}
