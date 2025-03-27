package com.jhojan.curso.springboot.jpa.repositories;

import com.jhojan.curso.springboot.jpa.dto.PersonDTO;
import com.jhojan.curso.springboot.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.id in (:ids)")
    List<Person> getPersonsByIds(List<Long> ids);

    @Query("SELECT p FROM Person p WHERE p.id not in (:ids)")
    List<Person> getPersonsByNotIds(List<Long> ids);

    @Query("SELECT p FROM Person p WHERE p.id = (SELECT MAX(p.id) FROM Person p)")
    Optional<Person> getLastRegistration();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name) = (SELECT MIN(LENGTH(p.name)) FROM Person p) ")
    List<Object[]> getShorterName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    Object getResumeAggregationFunction();

    @Query("select min(length(p.name)) from Person p")
    Long getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    Long getMaxLengthName();

    @Query("SELECT p.name, length(p.name) FROM Person p")
    List<Object[]> getPersonNameLength();

    @Query("SELECT COUNT(p) FROM Person p")
    Long getTotalPersons();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long getMinId();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long getMaxId();

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN 'J' AND 'Q'")
    List<Person> findAllBetweenName();

    List<Person> findByIdBetweenOrderByNameDescLastNameDesc(Long idAfter, Long idBefore);

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN 2 AND 5 ORDER BY p.id DESC")
    List<Person> findAllBetweenId();

    @Query("SELECT UPPER(p.name), LOWER(p.lastName), UPPER('LANGUAGE: ' || p.programmingLanguage ) FROM Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage)) FROM Person p")
    Long totalLanguages();

    @Query("select new com.jhojan.curso.springboot.jpa.dto.PersonDTO(p.name, p.lastName) from Person p")
    List<PersonDTO> findAllPersonDTO();

    @Query("select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllPersonalizedObjectPerson();

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPersonDataList();

    @Query("select p.id, p.name, p.lastName, p.programmingLanguage from Person p where p.id = :id")
    Object obtenerPersonaDataById(Long id);

    @Query("select concat(p.name, ' ', p.lastName) from Person p where p.id = :id")
    String getFullNameById(Long id);

    @Query("select p.name from Person p where p.id = :id")
    String getNameById(Long id);

    @Query("select p from Person p where p.id = :id")
    Optional<Person> findOne(Long id);

    Optional<Person> findByName(String name);

    @Query("select p from Person p where p.name like %:name%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage = :lenguajeProgramacion")
    List<Person> buscarPorLenguajeProgramacion(String lenguajeProgramacion);

    List<Person> findByNameAndProgrammingLanguage(String name, String programmingLanguage);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> getPersonData();
}
