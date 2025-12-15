package ${package}.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Example request DTO for API calls.
 * <p>
 * Create DTOs matching your API's request schemas.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleRequestDto {

    private String name;
    private String job;
}
