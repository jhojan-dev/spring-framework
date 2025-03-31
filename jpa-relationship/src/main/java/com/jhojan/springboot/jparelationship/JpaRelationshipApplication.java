package com.jhojan.springboot.jparelationship;

import com.jhojan.springboot.jparelationship.entities.*;
import com.jhojan.springboot.jparelationship.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class JpaRelationshipApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final InvoiceRepository invoiceRepository;
    private final ClientDetailsRepository clientDetailsRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public JpaRelationshipApplication(
            ClientRepository clientRepository,
            InvoiceRepository invoiceRepository,
            ClientDetailsRepository clientDetailsRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.clientRepository = clientRepository;
        this.invoiceRepository = invoiceRepository;
        this.clientDetailsRepository = clientDetailsRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // manyToOne();
        // manyToOneFindById();
        // oneToMany();
        // oneToManyFindById();
        // removeAddressFindById();
        // oneToManyBidirectional();
        // removeInvoiceBidirectionalFindById();
        // removeInvoiceBidirectional();
        // oneToOne();
        // oneToOneById();
        // oneToOneBidirectional();
        // oneToOneBidirectionalFindById();
        // manyToMany();
        // manyToManyFind();
        // manyToManyRemoveFind();
        // manyToManyRemove();
        // manyToManyBidirectional();
        // manyToManyBidirectionalRemove();
        // manyToManyBidirectionalFind();
        manyToManyRemoceBidirectionalFind();
    }

    @Transactional
    public void manyToManyRemoceBidirectionalFind() {
        Student student1 = studentRepository.findById(1L).orElseThrow();
        Student student2 = studentRepository.findById(2L).orElseThrow();

        Course course1 = courseRepository.findOne(1L).orElseThrow();
        Course course2 = courseRepository.findOne(2L).orElseThrow();

        student1.addCourse(course1).addCourse(course2);
        student2.addCourse(course2);

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);

        studentRepository.findOne(1L).ifPresent(student -> {
            courseRepository.findOne(1L).ifPresent(course -> {
                student.getCourses().remove(course);
                studentRepository.save(student);
                System.out.println("student = " + student);
            });
        });
    }

    @Transactional
    public void manyToManyBidirectionalFind() {
        Student student1 = studentRepository.findById(1L).orElseThrow();
        Student student2 = studentRepository.findById(2L).orElseThrow();

        Course course1 = courseRepository.findOne(1L).orElseThrow();
        Course course2 = courseRepository.findOne(2L).orElseThrow();

        student1.addCourse(course1).addCourse(course2);
        student2.addCourse(course2);

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);
    }

    @Transactional
    public void manyToManyBidirectionalRemove() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Jan", "Doe");

        Course course1 = new Course("Java Master", "Andres");
        Course course2 = new Course("Spring Boot", "Jhojan");

        student1.addCourse(course1).addCourse(course2);
        student2.addCourse(course2);

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);

        studentRepository.findOne(3L).ifPresent(student -> {
            courseRepository.findOne(3L).ifPresent(course -> {
                student.getCourses().remove(course);
                studentRepository.save(student);
                System.out.println("student = " + student);
            });
        });
    }

    @Transactional
    public void manyToManyBidirectional() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Jan", "Doe");

        Course course1 = new Course("Java Master", "Andres");
        Course course2 = new Course("Spring Boot", "Jhojan");

        student1.addCourse(course1).addCourse(course2);
        student2.addCourse(course2);

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);

    }

    @Transactional
    public void manyToManyRemove() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Jan", "Doe");

        Course course1 = new Course("Java Master", "Andres");
        Course course2 = new Course("Spring Boot", "Jhojan");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);

        studentRepository.findOne(3L).ifPresent(student -> {
            courseRepository.findById(3L).ifPresent(course -> {
                student.getCourses().remove(course);
                studentRepository.save(student);
                System.out.println("student = " + student);
            });
        });
    }

    @Transactional
    public void manyToManyRemoveFind() {
        Student student1 = studentRepository.findById(1L).orElseThrow();
        Student student2 = studentRepository.findById(2L).orElseThrow();

        Course course1 = courseRepository.findById(1L).orElseThrow();
        Course course2 = courseRepository.findById(2L).orElseThrow();

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);

        studentRepository.findOne(1L).ifPresent(student -> {
           courseRepository.findById(2L).ifPresent(course -> {
               student.getCourses().remove(course);
               studentRepository.save(student);
               System.out.println("student = " + student);
           });
        });
    }

    @Transactional
    public void manyToManyFind() {
        Student student1 = studentRepository.findById(1L).orElseThrow();
        Student student2 = studentRepository.findById(2L).orElseThrow();

        Course course1 = courseRepository.findById(1L).orElseThrow();
        Course course2 = courseRepository.findById(2L).orElseThrow();

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);
    }

    @Transactional
    public void manyToMany() {
        Student student1 = new Student("Jano", "Pura");
        Student student2 = new Student("Jan", "Doe");

        Course course1 = new Course("Java Master", "Andres");
        Course course2 = new Course("Spring Boot", "Jhojan");

        student1.setCourses(Set.of(course1, course2));
        student2.setCourses(Set.of(course2));

        Iterable<Student> saves = studentRepository.saveAll(List.of(student1, student2));
        saves.forEach(System.out::println);
    }

    @Transactional
    public void oneToOneBidirectionalFindById() {
        clientRepository.findOne(2L)
                .ifPresent((client) -> {
                    ClientDetails clientDetails = new ClientDetails(true, 5000);
                    client.setClientDetails(clientDetails);

                    clientRepository.save(client);
                    System.out.println("client = " + client);
                });
    }

    @Transactional
    public void oneToOneBidirectional() {
        Client client = new Client("Erba", "Pura");
        ClientDetails clientDetails = new ClientDetails(true, 5000);

        client.setClientDetails(clientDetails);

        clientRepository.save(client);
        System.out.println("client = " + client);
    }

    @Transactional
    public void oneToOneById() {
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        clientDetailsRepository.save(clientDetails);

        clientRepository.findOne(2L)
                .ifPresent(client -> {
                    client.setClientDetails(clientDetails);
                    clientRepository.save(client);
                    System.out.println("client = " + client);
                });
    }

    @Transactional
    public void oneToOne() {
        ClientDetails clientDetails = new ClientDetails(true, 5000);
        clientDetailsRepository.save(clientDetails);

        Client client = new Client("Erba", "Pura");
        client.setClientDetails(clientDetails);
        clientRepository.save(client);

        System.out.println("client = " + client);
    }

    @Transactional
    public void removeInvoiceBidirectional() {
        Optional<Client> optionalClient = Optional.of( new Client("Fran", "Moras") );

        optionalClient.ifPresent(client -> {
                    Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
                    Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

                    client.addInvoice(invoice1).addInvoice(invoice2);

                    Client clientDB = clientRepository.save(client);
                    System.out.println("clientDB = " + clientDB);
                });

        optionalClient.ifPresent(client -> {
            invoiceRepository.findById(3L)
                    .ifPresent(invoice -> {
                        client.removeInvoice(invoice);

                        Client clientDB = clientRepository.save(client);
                        System.out.println("clientDB = " + clientDB);
                    });
        });
    }

    @Transactional
    public void removeInvoiceBidirectionalFindById() {
        clientRepository.findOne(1L)
                .ifPresent(client -> {
                    Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
                    Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

                    client.addInvoice(invoice1).addInvoice(invoice2);

                    Client clientDB = clientRepository.save(client);
                    System.out.println("clientDB = " + clientDB);
                });

        clientRepository.findOne(1L).ifPresent(client -> {
            invoiceRepository.findById(2L)
                    .ifPresent(invoice -> {
                        client.removeInvoice(invoice);

                        Client clientDB = clientRepository.save(client);
                        System.out.println("clientDB = " + clientDB);
                    });
        });
    }

    @Transactional
    public void oneToManyBidirectionalFindById() {
        clientRepository.findOne(1L)
                .ifPresent(client -> {
                    Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
                    Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

                    client.addInvoice(invoice1).addInvoice(invoice2);

                    Client clientDB = clientRepository.save(client);
                    System.out.println("clientDB = " + clientDB);
                });
    }

    @Transactional
    public void oneToManyBidirectional() {
        Client client = new Client("John", "Smith");

        Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
        Invoice invoice2 = new Invoice("Compras de oficina", 8000L);
        
        client.addInvoice(invoice1).addInvoice(invoice2);
        
        Client clientDB = clientRepository.save(client);
        System.out.println("clientDB = " + clientDB);
    }

    @Transactional
    public void removeAddressFindById() {
        clientRepository.findById(2L)
                .ifPresent(client -> {
                    Address address1 = new Address("El Verjel", 1234);
                    Address address2 = new Address("Vasco de Gama", 9875);

                    client.setAddresses(Set.of(address1, address2));
                    clientRepository.save(client);
                    System.out.println("client = " + client);

                    clientRepository.findOneWithAddresses(2L).ifPresent(c -> {
                        c.getAddresses().remove(address2);
                        clientRepository.save(c);
                        System.out.println("c = " + c);
                    });
                });

    }

    @Transactional
    public void removeAddress() {
        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("El Verjel", 1234);
        Address address2 = new Address("Vasco de Gama", 9875);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);
        System.out.println("client = " + client);

        // ::::: La eliminamos
        Client clientDB = clientRepository.findById(3L).orElseThrow();
        clientDB.getAddresses().remove(address1);
        clientRepository.save(clientDB);
        System.out.println("client = " + clientDB);
    }

    @Transactional
    public void oneToManyFindById() {
        clientRepository.findById(2L).ifPresent(client -> {
            Address address1 = new Address("El Verjel", 1234);
            Address address2 = new Address("Vasco de Gama", 9875);
            client.setAddresses(Set.of(address1, address2));

            clientRepository.save(client);

            System.out.println("client = " + client);
        });
    }

    @Transactional
    public void oneToMany() {
        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("El Verjel", 1234);
        Address address2 = new Address("Vasco de Gama", 9875);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);
        System.out.println("client = " + client);
    }

    @Transactional
    public void manyToOneFindById() {
        clientRepository.findById(1L)
                .ifPresent(client -> {
                    Invoice invoice = new Invoice("Compras de oficina", 2000L);
                    invoice.setClient(client);

                    Invoice invoiceDB = invoiceRepository.save(invoice);
                    System.out.println("invoiceDB = " + invoiceDB);
                });
    }

    @Transactional
    public void manyToOne() {
        Client client = new Client("John", "Smith");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);
        Invoice invoiceDB = invoiceRepository.save(invoice);

        System.out.println("invoiceDB = " + invoiceDB);
    }

}
