package de.neuefische.studentdbbackend.student;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewStudentDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+", message = "First name must only contain letters")
    @NotBlank(message = "First name is required")
    @Size(min = 2, message = "First name must be at least 2 characters long")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+", message = "Last name must only contain letters")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    private String lastName;

    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "Course must only contain letters and numbers")
    @NotBlank(message = "Course is required")
    private String course;

}
