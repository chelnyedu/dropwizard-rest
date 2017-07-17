package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.dao.mappers.ContactMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import org.junit.Test;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.fest.assertions.api.Assertions.assertThat;
/**
 * Created by user on 17.07.17.
 */
public class TestContactMapper {
    ContactMapper map = new ContactMapper();
    Contact contact = new Contact(4, "MIR", "RIM", "789466");
    ResultSet resultSet = mock(ResultSet.class);

    @Test
    public void testMapper() throws SQLException {
        when(resultSet.getInt("id")).thenReturn(4);
        when(resultSet.getString("firstName")).thenReturn("MIR");
        when(resultSet.getString("lastName")).thenReturn("RIM");
        when(resultSet.getString("phone")).thenReturn("789466");
        StatementContext ctx = mock(StatementContext.class);
        Contact con = map.map(4, resultSet, ctx);
        assertThat(con).isEqualTo(contact);



    }

}
