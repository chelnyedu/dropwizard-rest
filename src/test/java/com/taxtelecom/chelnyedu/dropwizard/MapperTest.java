package com.taxtelecom.chelnyedu.dropwizard;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.skife.jdbi.v2.StatementContext;

import com.taxtelecom.chelnyedu.dropwizard.dao.mappers.ContactMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;

public class MapperTest {
	ContactMapper mapper = new ContactMapper();
	@Test
	public void mapTest() throws SQLException {
		ResultSet r = mock(ResultSet.class);
		when(r.getInt("id")).thenReturn(1);
		when(r.getString("firstName")).thenReturn("Jade");
		when(r.getString("lastName")).thenReturn("Doe");
		when(r.getString("phone")).thenReturn("0123456789");
		when(r.getString("mail")).thenReturn("s@re.ru");
		when(r.getString("comment")).thenReturn("");
		StatementContext ctx = mock(StatementContext.class);
		Contact con = new Contact(1 ,"Jade", "Doe", "0123456789", "s@re.ru", "");
		Contact contact = mapper.map(0, r, ctx);
		assertThat(contact).isEqualTo(con);
	}
}
