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
$2a$10$DErmSjtLvYs4Nolt3E0WHu5OLvXDiY1hDsv/FfcycvfGSDY1rR7EK
$2a$10$VR.WR9BF4YBGzyOvqxtMH.ZQs.mie8.CvVSX1U3TOB3vzrf5.nCUy
$2a$10$DanoWjAy3KB1Szr/nrVSd.Q1.RPhVENTMJYnRPu83TjMhbo177SOi
$2a$10$DactEKTF3uHnxCFrgnUpB.Oqc8u9OOHgd0txRMimJEZ609/QKX.x6
 */