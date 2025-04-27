package cl.duoc.fullstack.web.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;

@JsonPropertyOrder({ "status", "timestamp", "data" }) 
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseWrapper<T> {
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private T data;
}

