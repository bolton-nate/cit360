/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author natebolton
 */
public class Rot13Control {
    
    public static String encrypt(String s) {
        StringBuilder sb = new StringBuilder();
        //System.out.println(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'm') {
                c += 13;
            } else if  (c >= 'A' && c <= 'M') {
                c += 13;
            } else if  (c >= 'n' && c <= 'z') {
                c -= 13;
            } else if  (c >= 'N' && c <= 'Z') {
                c -= 13;
            }
            sb.append(c);
        }
        //System.out.println(s);
        return sb.toString();
    }
    
//    public static String decrypt(String args) {
//        String s = args[0];
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if       (c >= 'a' && c <= 'm') c -= 13;
//            else if  (c >= 'A' && c <= 'M') c -= 13;
//            else if  (c >= 'n' && c <= 'z') c += 13;
//            else if  (c >= 'N' && c <= 'Z') c += 13;
//        }
//        return s;
//    }
}
