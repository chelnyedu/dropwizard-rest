package com.taxtelecom.chelnyedu.dropwizard.dao;

import com.taxtelecom.chelnyedu.dropwizard.dao.mappers.ContactMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;


/**
 * Created by user on 07.07.17.
 */
@RegisterMapper(ContactMapper.class)
public interface ContactDao {
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
