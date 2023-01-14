package com.birthdayemail.userdetails.repository;

import com.birthdayemail.userdetails.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(value = "select * from person_details where to_char(CURRENT_DATE, 'MM-dd')=to_char(day_month, 'MM-dd')", nativeQuery = true)
    List<PersonEntity> findAllByDayMonth();
}
