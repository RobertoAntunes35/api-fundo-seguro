package br.com.debtscredits.debtscreditsapi.config;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucessResponse {
    private Integer status;
    private String message;

    public static SucessResponse create(String message) {
        return SucessResponse
        .builder()
        .status(HttpStatus.OK.value())
        .build();
    }
}
