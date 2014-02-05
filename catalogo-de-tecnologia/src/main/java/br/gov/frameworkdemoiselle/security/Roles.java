package br.gov.frameworkdemoiselle.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author escritorio
 */
public class Roles implements IRoles {

    /**
     *
     * @param role
     * @return
     */
    public static List<String> getRole(Short role) {
        List<String> list = new ArrayList<String>();
        switch (role) {
            case 1:
                list.add(USER);
                break;
            case 2:
                list.add(MANAGER);
                break;
            case 3:
                list.add(USER);
                list.add(MANAGER);
                break;
            case 4:
                list.add(ADMINISTRATOR);
                break;
            case 5:
                list.add(USER);
                list.add(ADMINISTRATOR);
                break;
            case 6:
                list.add(MANAGER);
                list.add(ADMINISTRATOR);
                break;
            case 7:
                list.add(USER);
                list.add(MANAGER);
                list.add(ADMINISTRATOR);
                break;
            default:
                list.add(INVALID);
                break;
        }
        return list;
    }

    /**
     *
     * @return
     */
    public static List<String> getRolesList() {
        List<String> list = new ArrayList<String>();
        list.add(USER);
        list.add(MANAGER);
        list.add(ADMINISTRATOR);
        list.add(INVALID);
        return list;
    }

    /**
     *
     * @return
     */
    public static HashMap<Short, String> getRolesListAsMap(){

    	HashMap<Short, String> papeis = new HashMap<Short, String>();

    	papeis.put(getRole(USER), USER);
    	papeis.put(getRole(MANAGER), MANAGER);
    	papeis.put(getRole(ADMINISTRATOR), ADMINISTRATOR);
    	papeis.put(getRole(INVALID), INVALID);

    	return papeis;
    }

    /**
     *
     * @param listRole
     * @return
     */
    public static Long getRole(List<String> listRole) {
        Long number = 0L;

        if (listRole.contains(USER)) {
            number += 1L;
        }
        if (listRole.contains(MANAGER)) {
            number += 2L;
        }
        if (listRole.contains(ADMINISTRATOR)) {
            number += 4L;
        }
        if (listRole.contains(INVALID)) {
            number += 8L;
        }

        return number;
    }

    /**
     *
     * @param role
     * @return
     */
    public static Short getRole(String role) {

        if (role.equals(USER)) {
            return 1;
        }
        if (role.equals(MANAGER)) {
            return 2;
        }
        if (role.equals(ADMINISTRATOR)) {
            return 4;
        }

        return 0;
    }

    private Roles() {
    }
}
