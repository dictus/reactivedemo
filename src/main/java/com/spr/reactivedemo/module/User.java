// Create a Spring Boot project with the following structure:

// src/main/java/com/example/medicineportal
// ├── controller
// │   ├── MedicineController.java
// │   └── UserController.java
// ├── entity
// │   ├── Medicine.java
// │   ├── Order.java
// │   └── User.java
// ├── repository
// │   ├── MedicineRepository.java
// │   ├── OrderRepository.java
// │   └── UserRepository.java
// ├── service
// │   ├── MedicineService.java
// │   ├── OrderService.java
// │   └── UserService.java
// └── MedicinePortalApplication.java


// src/main/java/com/example/medicineportal/entity/User.java
package com.spr.reactivedemo.module;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Data
@Table("users")
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private String roles; // e.g., "USER,ADMIN"

    // Getters and Setters
}


