package co.pragra.pragracoffetime.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ErrorDto {
    @ApiModelProperty(example = "400")
    private int statusCode;
    @ApiModelProperty(example = "Customer not found")
    private String message;
    @ApiModelProperty
    private Instant timestamp;
    @ApiModelProperty(example = "API938387")
    private String applicationId;
}
