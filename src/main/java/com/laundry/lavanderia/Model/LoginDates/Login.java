package com.laundry.lavanderia.Model.LoginDates;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
    private boolean remember;
}
