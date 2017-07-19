package com.taxtelecom.chelnyedu.dropwizard.dao;


import com.taxtelecom.chelnyedu.dropwizard.dao.mappers.ContactMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface ContactDAO {
    @Mapper(ContactMapper.class)

    @SqlQuery("select * from contact")
    List<Contact> getAllContact();

    @SqlQuery("select * from contact where id = :id")
    Contact getContactById(@Bind("id") int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into contact (firstname, lastname, phone, mail, comment) values (:firstname, :lastname, :phone, :mail, :comment)")
    int createContact(@Bind("firstname") String firstname,
                      @Bind("lastname") String lastname,
                      @Bind("phone") String phone,
                      @Bind("mail") String mail,
                      @Bind("comment") String comment);

    @SqlUpdate("update contact set firstname = :firstname, lastname = :lastname, phone = :phone, mail = :mail, comment = :comment where id = :id")
    void updateContact(@Bind("id") int id,
                       @Bind("firstname") String firstname,
                       @Bind("lastname") String lastname,
                       @Bind("phone") String phone,
                       @Bind("mail") String mail,
                       @Bind("comment") String comment);
    @SqlUpdate("delete from contact where id = :id")
    void deleteContact(@Bind("id") int id);
}
