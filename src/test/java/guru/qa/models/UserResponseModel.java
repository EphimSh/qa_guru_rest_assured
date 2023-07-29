package guru.qa.models;

import lombok.Data;

@Data
public class UserResponseModel {
    String name, job, createdAt, updatedAt;
    int id;
}
