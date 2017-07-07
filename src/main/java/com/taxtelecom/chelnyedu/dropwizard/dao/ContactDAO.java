package com.taxtelecom.chelnyedu.dropwizard.dao;


import com.taxtelecom.chelnyedu.dropwizard.dao.mappers.ContactMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ContactDAO {
    @Mapper(ContactMapper.class)
    @SqlQuery("select * from contact where id = :id")
    Contact getContactById(@Bind("id") int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into contact (firstname, lastname, phone) values (:firstname, :lastname, :phone)")
    int createContact(@Bind("firstname") String firstname,
                      @Bind("lastname") String lastname,
                      @Bind("phone") String phone);

    @SqlUpdate("update contact set firstname = :firstname, lastname = :lastname, phone = :phone where id = :id")
    void updateContact(@Bind("id") int id,
                       @Bind("firstname") String firstname,
                       @Bind("lastname") String lastname,
                       @Bind("phone") String phone);

    @SqlUpdate("delete from contact where id = :id")
    void deleteContact(@Bind("id") int id);
}
