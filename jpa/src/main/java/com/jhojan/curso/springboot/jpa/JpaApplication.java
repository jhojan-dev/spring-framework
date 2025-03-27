package com.jhojan.curso.springboot.jpa;

import com.jhojan.curso.springboot.jpa.dto.PersonDTO;
import com.jhojan.curso.springboot.jpa.entities.Person;
import com.jhojan.curso.springboot.jpa.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    private final PersonRepository personRepository;

    public JpaApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // list();
        // findOne();
        // create();
        // update();
        // delete();
        // delete2();
        // personalizedQueries();
        // personalizedQueries2();
        // personalizedQueries3();
        // personalizedQueriesCase();
        // personalizedBetween();
        // queriesFunctionAggregation();
        // subQueries();
        whereIn();
    }

    @Transactional(readOnly = true)
    public void whereIn() {
        System.out.println(":::::::::: consulta where in");
        List<Long> ids = List.of(1L, 2L, 3L);
        List<Person> persons = personRepository.getPersonsByIds(ids);
        printList(persons);
        persons = personRepository.getPersonsByNotIds(ids);
        printList(persons);
    }

    @Transactional(readOnly = true)
    public void subQueries() {
        System.out.println("::::::: Consulta por el nombre mas corto y largo");
        List<Object[]> registers = personRepository.getShorterName();
        registers.forEach(reg -> {
            String name = (String) reg[0];
            Integer length = (Integer) reg[1];
            System.out.println("name: " + name + ", length: " + length);
        });

        System.out.println(":::::::::::: consulta para obtener el ultimo registro de persona");
        personRepository.getLastRegistration()
                .ifPresent(person -> System.out.println("last registration: " + person));
    }

    @Transactional(readOnly = true)
    public void queriesFunctionAggregation() {
        System.out.println("Personas totales: " + personRepository.getTotalPersons());
        System.out.println("Personas min id: " + personRepository.getMinId());
        System.out.println("Personas max id: " + personRepository.getMaxId());

        System.out.println("::::::: length");
        List<Object[]> list = personRepository.getPersonNameLength();
        list.forEach(objects -> System.out.println(objects[0] + " " + objects[1]));

        System.out.println("Nombre mas corto contiene: = " + personRepository.getMinLengthName());
        System.out.println("Nombre mas largo contiene: = " + personRepository.getMaxLengthName());

        System.out.println("::::::: resumen funciones de agregacion");
        Object[] resumenReg = (Object[]) personRepository.getResumeAggregationFunction();
        System.out.println("min = " + resumenReg[0]
                + ", max = " + resumenReg[1]
                + ", sum = " + resumenReg[2]
                + ", avg = " + resumenReg[3]
                + ", count = " + resumenReg[4]
        );
    }

    @Transactional(readOnly = true)
    public void personalizedBetween() {
        this.printList(personRepository.findAllBetweenId());
        this.printList(personRepository.findAllBetweenId());
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesCase() {
        personRepository
                .findAllPersonDataListCase()
                .forEach(objArr -> System.out.println(objArr[0] + " - " + objArr[1] + " - " + objArr[2]));
    }

    @Transactional(readOnly = true)
    public void personalizedQueries3() {
        System.out.println("::::::: Obtener todos los nombres");
        List<String> nombres = personRepository.findAllNames();
        List<String> namesDistinct = personRepository.findAllNamesDistinct();
        namesDistinct.forEach(System.out::println);

        List<PersonDTO> listPersonDTO = personRepository.findAllPersonDTO();
        listPersonDTO.forEach(System.out::println);

        System.out.println("::::::: Total language programming : " + personRepository.totalLanguages());
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        System.out.println("::::::: Consulta por objeto persona y lenguaje de programacion");
        List<Object[]> objectList = personRepository.findAllMixPersonDataList();

        objectList.forEach(objArr -> {
            System.out.println(objArr[0] + " tiene el lenguaje de programacion: " + objArr[1]);
        });

        List<Person> listCustomPerson = personRepository.findAllPersonalizedObjectPerson();
        listCustomPerson.forEach(System.out::println);

        List<PersonDTO> listPersonDTO = personRepository.findAllPersonDTO();
        listPersonDTO.forEach(System.out::println);

    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id para el nombre: ");
        Long id = scanner.nextLong();
        scanner.close();
        String nombre = personRepository.getNameById(id);
        System.out.println("El nombre es: " + nombre);

        String fullName = personRepository.getFullNameById(id);
        System.out.println("El full name es: " + fullName);

        System.out.println("============= consulta de campos por el id");
        Object[] personReg = (Object[]) personRepository.obtenerPersonaDataById(id);
        if (personReg.length > 0) {
            System.out.println("id= " + personReg[0] + " nombre= " + personReg[1] + " apellido= " + personReg[2] + " lenguaje= " + personReg[3]);
        }
    }

    @Transactional
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona a eliminar: ");
        Long id = scanner.nextLong();
        if (!personRepository.existsById(id)) {
            System.out.println("El persona no existe");
            return;
        }
        personRepository.deleteById(id);
        System.out.println("Persona eliminada.");
        scanner.close();
    }

    @Transactional
    public void delete2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona a eliminar: ");
        Long id = scanner.nextLong();
        personRepository
                .findById(id)
                .ifPresentOrElse(
                        personRepository::delete,
                        () -> System.out.println("La persona no existe.")
                );
    }

    @Transactional
    public void  update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Busque la persona por id: ");
        Long id = scanner.nextLong();
        Optional<Person> optPerson = personRepository.findById(id);
        optPerson.ifPresent(person -> {
            System.out.println("person = " + person);
            System.out.println("Ingrese el lenguaje de programacion: ");
            String programacion = scanner.next();
            person.setProgrammingLanguage(programacion);
            System.out.println("person = " + personRepository.save(person));
        });

        scanner.close();
    }

    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a name: ");
        String name = scanner.next();
        System.out.println("Please enter a surname: ");
        String lastName = scanner.next();
        System.out.println("Please enter a programming language: ");
        String programmingLanguage = scanner.next();
        scanner.close();

        Person person = personRepository.save(
                new Person(null, name, lastName, programmingLanguage)
        );

        Optional.of(person)
                .flatMap(personDB -> personRepository.findOne(person.getId()))
                .ifPresent(System.out::println);


    }

    public void findOne() {
        // personRepository.findById(8L).ifPresent(System.out::println);
        // personRepository.findOne(1L).ifPresent(System.out::println);
        // personRepository.findByName("PEPE").ifPresent(System.out::println);
        // personRepository.findOneLikeName("PE").ifPresent(System.out::println);
        personRepository.findByNameContaining("PE").ifPresent(System.out::println);
    }

    public void list() {
        //List<Person> persons = personRepository.buscarPorLenguajeProgramacion("JAVASCRIPT");
        // List<Person> persons = (List<Person>) personRepository.findAll();
        List<Person> persons = personRepository.findByNameAndProgrammingLanguage("JHOJAN", "JAVA");
        persons.forEach(System.out::println);

        List<Object[]> personsData = personRepository.getPersonData();
        personsData.forEach(objectData -> {
            System.out.println("Person Data: " + objectData[0] + " - " + objectData[1]);
        });
    }

    public <T> void printList(List<T> list) {
        list.forEach(System.out::println);
    }
}
