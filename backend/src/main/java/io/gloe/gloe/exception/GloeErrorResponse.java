package io.gloe.gloe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GloeErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
