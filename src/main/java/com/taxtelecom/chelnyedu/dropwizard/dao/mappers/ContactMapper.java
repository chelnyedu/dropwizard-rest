package com.taxtelecom.chelnyedu.dropwizard.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
/**
 * Created by user on 07.07.17.
 */
public class ContactMapper implements ResultSetMapper<Contact> {
    @Override
    public Contact map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Contact(r.getInt("id"), r.getString("firstName"), r.getString("lastName"), r.getString("phone"));
    }
}
