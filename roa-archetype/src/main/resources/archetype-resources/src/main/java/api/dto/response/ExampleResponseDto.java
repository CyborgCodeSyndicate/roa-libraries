package ${package}.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Example response DTO from API calls.
 * <p>
 * Create response DTOs matching your API's response schemas.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleResponseDto {

    private String id;
    private String status;
}
