package cl.duoc.fullstack.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse<T> {
    private String code;
    private T message;
}
