package ru.dins_сollaboration.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.dins_сollaboration.models.State;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDto {
    @ApiModelProperty(notes = "id PhoneDto")// swagger.. ex
    private Long id;

    private String number;

    private String text;

    private State state;

    private LocalDateTime createdTime;

    private Long userId;
}
