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

    @NotNull
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "Course must only contain letters and numbers")
    @NotBlank(message = "Course is required")
    private String courseOfStudies;

}
