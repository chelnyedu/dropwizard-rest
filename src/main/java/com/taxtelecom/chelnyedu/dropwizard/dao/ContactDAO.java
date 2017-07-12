package com.taxtelecom.chelnyedu.dropwizard.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import com.taxtelecom.chelnyedu.dropwizard.dao.mappers.ContactMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;

@RegisterMapper(ContactMapper.class)
public interface ContactDAO {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS contact (id SERIAL NOT NULL PRIMARY KEY, firstName varchar(64), "
			+ "firstName varchar(64), phone varchar(64))")
	  void createTable();
	
	@Mapper(ContactMapper.class)
	@SqlQuery("SELECT * FROM contact WHERE id = :id")
	Contact getContactById(@Bind("id") int id);
	
    @GetGeneratedKeys
    @MapResultAsBean
    @SqlUpdate("INSERT INTO contact (firstName, lastName, phone) VALUES (:firstName, :lastName, :phone)")
    int createContact(@Bind("firstName") String firstName, @Bind("lastName") String lastName,
                      @Bind("phone") String phone );

    @SqlUpdate("UPDATE contact SET firstName = :firstName, lastName = :lastName, phone = :phone WHERE id = :id")
    void updateContact(@Bind("id") int id, @Bind("firstName") String firstName,
                       @Bind("lastName") String lastName, @Bind("phone") String phone );

    @SqlUpdate("DELETE FROM contact WHERE id = :id")
    void deleteContact(@Bind("id") int id);
}
