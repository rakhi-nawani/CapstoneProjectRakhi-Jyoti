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

        String encodedPassword1 = enc.encode(TeamLeadPassword);
        String encodedPassword2 = enc.encode(EmployeePassword);
        String encodedPassword3 = enc.encode(ManagerPassword);
        String encodedPassword4 = enc.encode(AdminPassword);

        System.out.println(encodedPassword1 );
        System.out.println(encodedPassword2 );
        System.out.println(encodedPassword3 );
        System.out.println(encodedPassword4 );

    }
}


/*
$2a$10$8DHk1tXRvizyoteLnZ8Om.9QYtTKHPljviuAWl9pv19Va2Ka4qX0C
$2a$10$34fic031vNiKOTZhL/0dROVOPJsURKO4vW87Ar2SRmcx3AkDlO5Gi
$2a$10$xRdjaevHe4tuwuwQOgT3WOhjvp9G9BkmP.IkHzDG1e6yWaUbmor9u
$2a$10$FOmQ0k/oYwaBUfvIcsLf5eVlVe0Pos/DlLeIdDGC5ryBkOQk0nkge
 */