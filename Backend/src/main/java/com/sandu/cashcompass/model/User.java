package com.sandu.cashcompass.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document("Users")
@Getter
@Setter
public class User {
    @Id
    private String id;

    private String username;
    private String email;
    private String password;
}
