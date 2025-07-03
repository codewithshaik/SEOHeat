package com.shaiksnet.stepdefintions;


import io.cucumber.java.BeforeAll;

public class Test {

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("bs_username", "shaikmahaboobsub_HYZDSt*)");
        System.setProperty("bs_access_key", "KBJFMzHTAC3udHTnPa3*");
        System.setProperty("bs_proxy_user", "45");
        System.setProperty("bs_proxy_password", "Yucantcatchme");
        System.setProperty("mongo_username", "idldb_sit_user0B1");
        System.setProperty("mongo_password", "B5R9ELNoP6aS");
        System.setProperty("db2_username", "*"); // Please specify the actual username
        System.setProperty("db2_password", "*"); // Please specify the actual password
        System.setProperty("hsbenet_nemonableensuer", "testing1");
        System.setProperty("hsbenet_password", "your_password"); // Replace with the actual password
        System.setProperty("os_version", "11");
        System.setProperty("browser", "chrome");
        System.setProperty("browser_version", "latest"); // Replace with the actual version if needed
        System.setProperty("host", "Local");
        System.setProperty("mongo_uk_username", "CIS_USER");
        System.setProperty("mongo_uk_password", "SoLid2009");
    }
}
