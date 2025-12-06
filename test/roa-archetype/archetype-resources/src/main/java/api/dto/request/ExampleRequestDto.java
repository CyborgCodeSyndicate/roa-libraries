package ${package}.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Example request DTO used in archetype API tests.
 * Replace fields with your real request model when adapting the template.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleRequestDto {

    private String name;
    private String job;
}
