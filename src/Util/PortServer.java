/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nguye
 */
public class PortServer {

    public static ArrayList<String> listPort = new ArrayList<>(Arrays.asList("1433", "1434", "1435", "1436"));

    public static ArrayList<String> getListLinkServer(String serverPort) {
        ArrayList<String> list;
        list = switch (serverPort) {
            case "1434" ->
                new ArrayList<>(Arrays.asList("LINK2", "LINK3"));
            case "1435" ->
                new ArrayList<>(Arrays.asList("LINK1", "LINK3"));
            case "1436" ->
                new ArrayList<>(Arrays.asList("LINK1", "LINK2"));
            default ->
                null;
        };
        return list;
    }

}
