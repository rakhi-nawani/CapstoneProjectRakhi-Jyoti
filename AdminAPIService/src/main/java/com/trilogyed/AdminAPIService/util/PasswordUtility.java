package com.trilogyed.AdminAPIService.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {
    public static void main(String[] args) {
        PasswordEncoder enc = new BCryptPasswordEncoder();

        String AdminPassword = "IsThisPasswordOK?";
        String ManagerPassword = "!2@3#^^5";
        String TeamLeadPassword = "PaSsWorD";
        String EmployeePassword = "ThisPasswordIsNotOK?";

        String encodedPassword1 = enc.encode(AdminPassword);
        String encodedPassword2 = enc.encode(ManagerPassword);
        String encodedPassword3 = enc.encode(TeamLeadPassword);
        String encodedPassword4 = enc.encode(EmployeePassword);

        System.out.println(encodedPassword1 );
        System.out.println(encodedPassword2 );
        System.out.println(encodedPassword3 );
        System.out.println(encodedPassword4 );

    }
}


/*
$2a$10$tzvtyhT.XBgVc8os0v3T3.ADkgELWaHq0eLsQF8v6CeJT4NMre/4e
$2a$10$3HknTyr8OEs3F4eRQTwXGOrdrsDNx/MULy8goHGI9v.hnViSkP9w6
$2a$10$./BSW9mK910lUe3S5MvXyuTnYDK2/1YZnpICQ3fmtHSAjWWarANaS
$2a$10$VTqgqIKwj9jI7CnMMVwJFeYbufphP0OzLSa76F4ZxheomeuLabj6S
 */