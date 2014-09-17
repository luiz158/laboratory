/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demoiselle.drails;

import java.io.FileWriter;
import java.sql.DatabaseMetaData;
import static java.sql.DriverManager.println;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 *
 * @author 70744416353
 */
public class Database {

    /**
     *
     * @throws SQLException
     * @throws Exception
     */
    protected static void reverseEngineer() {

    }

    protected Table tableDetail(String tableName) {
        return null;
    }

}
