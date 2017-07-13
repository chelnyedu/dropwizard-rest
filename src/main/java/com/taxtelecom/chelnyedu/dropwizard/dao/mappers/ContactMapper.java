package com.taxtelecom.chelnyedu.dropwizard.dao.mappers;


import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.*;

public class ContactMapper implements  ResultSetMapper<Contact> {

    public Contact map(int index, ResultSet r, StatementContext ctx)throws SQLException{

        return new Contact(r.getInt("id"),
                r.getString("firstName"),
                r.getString("lastName"),
                r.getString("phone"));
    }
}
