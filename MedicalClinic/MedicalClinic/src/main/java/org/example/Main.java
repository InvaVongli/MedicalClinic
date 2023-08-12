package org.example;

import org.example.configuration.HibernateUtils;
import org.example.repositories.AppointmentRepository;
import org.example.repositories.DoctorRepository;
import org.example.repositories.ReportRepository;
import org.example.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLOutput;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DoctorRepository doctorRepository = new DoctorRepository(session, transaction);
        UserRepository userRepository = new UserRepository(session, transaction);
        AppointmentRepository appointmentRepository = new AppointmentRepository(session, transaction);
        ReportRepository reportRepository = new ReportRepository(session, transaction);
        Doctor doctorOrtoped = new Doctor("mmmm", "jjj", "kkk");
        Appointment appointment=new Appointment();
        Report report=new Report();
        User user=new User();



        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("You are:");
            System.out.println("1-- ADMINISTRATOR");
            System.out.println("2-- EMPLOYEE");
            System.out.println("3-- DOCTOR");
            System.out.print("Please Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You selected Option ADMINISTRATOR.");
                    boolean adminMenu = true;
                    while (adminMenu) {
                        System.out.println("Please select an option:");
                        System.out.println("1. Add a doctor to the list");
                        System.out.println("2. View the list of doctors");
                        System.out.println("3. Delete doctor from list");
                        System.out.println("4. Update doctor");
                        System.out.println("5. Select doctor from specialization");
                        System.out.println("6. EXIT");
                        int adminMenuChoice = scanner.nextInt();
                        switch (adminMenuChoice) {
                            case 1:
                                int pasword = 1;
                                System.out.println("You must login first");
                                System.out.println("Please insert your pasword!!");
                                if (scanner.nextInt() == pasword) {
                                    System.out.println("You succesfuly logedin");
                                    System.out.println("1.insert doctor manually");
                                    System.out.println("2.insert prepared doctor");
                                    int chosedoc = scanner.nextInt();
                                    if (chosedoc == 1) {
                                        System.out.println("Doctor Name:");
                                        String doctorName = scanner.next();
                                        System.out.println("Doctor Username :");
                                        String doctorUserName = scanner.next();
                                        System.out.println("Doctor Specialization");
                                        String doctorSpecialization = scanner.next();
                                        doctorOrtoped.setDoctorName(doctorName);
                                        doctorOrtoped.setUsername(doctorUserName);
                                        doctorOrtoped.setSpecialization(doctorSpecialization);
                                        doctorRepository.insertDoctor(doctorOrtoped);
                                        System.out.println("doctor succesfully inserted manually");
                                    } else
                                        doctorRepository.insertDoctor(doctorOrtoped);
                                    System.out.println(doctorOrtoped + "inserted successfuly");
                                } else System.out.println("wrong pasword");

                                break;
                            case 2:
                                doctorRepository.selectAllDoctors();

                                break;
                            case 3:
                                System.out.println("insert id of doctor to delete");
                                doctorRepository.deleteDoctors(scanner.next());
                                System.out.println("doctor deleted succfuly1");
                                break;
                            case 4:
                                doctorRepository.updateDoctor(doctorOrtoped);
                                break;
                            case 5:
                                // doctorRepository.getAllBySpecialization(doctorOrtoped)
                                break;
                            case 6:
                                adminMenu = false;
                            default:
                                System.out.println("Invalid option. Please choose again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("You selected Option EMPLOYEE.");
                    boolean employeeMenu = true;
                    while (employeeMenu) {
                        System.out.println("Please select a option");
                        System.out.println("1.Create an appointment");
                        System.out.println("2.Update an appointment");
                        System.out.println("3.Delete an appointment");
                        System.out.println("4.Select doctor by speciality");
                        System.out.println("5.EXIT");
                        int adminMenuChoise = scanner.nextInt();
                        switch (adminMenuChoise) {
                            case 1:
                                int employeePasword = 2;
                                System.out.println("You must login first");
                                System.out.println("Please insert your pasword!!");

                                if (scanner.nextInt() == employeePasword) {
                                    System.out.println("You succesfully loged in");
                                    scanner.nextLine();

                                    System.out.println("Patient Name: ");
                                    String patientName = scanner.nextLine();

                                    System.out.print("Begin At (yyyy-MM-dd HH:mm:ss): ");
                                    String beginAt = scanner.nextLine();
                                    LocalDateTime beginTime = LocalDateTime.parse(beginAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                                    System.out.print("End At (yyyy-MM-dd HH:mm:ss): ");
                                    String endAt = scanner.nextLine();
                                    LocalDateTime endTime = LocalDateTime.parse(endAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                                    System.out.print("Note for Patient: ");
                                    String note = scanner.nextLine();

                                    System.out.print("Status: ");
                                    String status = scanner.next();
                                    // appointment.setId(appointmentId);
                                    appointment.setPatientName(patientName);
                                    appointment.setBeginAt(beginTime);
                                    appointment.setEndsAt(endTime);
                                    appointment.setNote(note);
                                    appointment.setStatus(status);
                                    appointmentRepository.create(appointment);
                                    System.out.println("success");

                                    //2023-09-12 08:00:00


                                } else
                                    System.out.println("incorrect pasword");
                                break;
                            case 2:
                                appointmentRepository.update(appointment);
                                break;
                            case 3:
                                System.out.println("Please select id to delete Appointment");
                                appointmentRepository.deleteById(scanner.nextLong());
                                System.out.println("Succfully deleted ");
                                break;
                            case 4:
                                System.out.println("Needed Speciality :");
                                doctorRepository.getAllBySpecialization(scanner.next());
                                break;
                            case 5:
                                employeeMenu = false;
                                break;

                        }


                    }
                    break;
                case 3:
                    System.out.println("You selected Option DOCTOR");

                    boolean doctorMenu = true;
                    while (doctorMenu) {
                        System.out.println("Please select a option");
                        System.out.println("1.Create a report");
                        System.out.println("2.Get all Reports");
                        System.out.println("3.Delete a report");
                        System.out.println("4.EXIT");
                        int doctorMenuChoise = scanner.nextInt();
                        switch (doctorMenuChoise) {

                            case 1:
                                int doctorPasword = 3;

                                System.out.println("You must login first as Doctor");
                                System.out.println("Please insert your pasword!!");
                                if(scanner.nextInt()==doctorPasword){
                                    System.out.println("Successfuly loged in");
                                }else System.out.println("incorrect pasword");
                                System.out.println("Please write a desritption on raport");
                                String description= scanner.next();
                                System.out.println("report id ");
                                report.setDescription(description);
                                reportRepository.createReport(report);

                                break;
                            case 2:
                                reportRepository.getAllReports();
                                break;
                            case 3:
                                reportRepository.deleteReportById(scanner.nextLong());
                                break;
                            case 4: doctorMenu=false;

                        }


                    }

                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
// boolean menuActive = true;
                   /* while (menuActive) {
                        System.out.println("Submenu:");
                        System.out.println("1. Suboption X");
                        System.out.println("2. Suboption Y");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Select an option: ");

                        int subChoice = scanner.nextInt();

                        switch (subChoice) {
                            case 1:
                                System.out.println("You selected Suboption X.");
                                break;
                            case 2:
                                System.out.println("You selected Suboption Y.");
                                break;
                            case 3:
                                menuActive = false;
                                break;
                            default:
                                System.out.println("Invalid suboption. Please select again.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
        */










        /*
        while (true) {
            try {


                Scanner scanner = new Scanner(System.in);
                System.out.println("you are ");
                System.out.println("1-- ADMINISTRATOR");
                System.out.println("2-- EMPLOYEE");
                System.out.println("3-- DOCTOR");

                switch (scanner.nextInt()) {

                    case 1:
                        System.out.println("you are Administrator");
                        System.out.println("1.Shto doktor ne liste");
                        System.out.println("2.shiko listen e doktoreve");
                        System.out.println("3.zgjidhni id e doktorit per vizite");

                        switch (scanner.nextInt()){
                            case 1:
                                doctorRepository.insertDoctor(doctorOrtoped);
                                break;
                            case 2:
                                doctorRepository.selectAllDoctors();
                                break;
                        }break;


                    case 2:
                        System.out.println("you are Employee");
                        break;
                    case 3:
                        System.out.println("you are Doctor");
                        break;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }
}





*/





     /*   while (true) {
            try {

                Scanner scanner=new Scanner(System.in);
                System.out.println("zgjidhni nje nga veprimet qe deshironi te beni");
                System.out.println("1.Shto doktor ne liste");
                System.out.println("2.shiko listen e doktoreve");
                System.out.println("3.zgjidhni id e doktorit per vizite");
                System.out.println("4.zgjidhni llojin e doktorit profesionin");
                System.out.println("zgjidhni nje appointment");


               // String ShtoDoktor= scanner.next();
            switch (scanner.nextInt()){

                case 1:
                    doctorRepository.insertDoctor(doctorOrtoped);
                    System.out.println("Sukses!!");
                    System.out.println("Enrolled Succesfuly");
                break;
                case 2:
                    doctorRepository.selectAllDoctors();
                break;
                case 3:
                    System.out.println("zgjidhni numrin e id te doktorit ");
                    doctorRepository.getById(scanner.nextInt());
                    break;
                case 4:
                    System.out.println("zgjidhni llojin e doktorit sipas profesionit");
                    doctorRepository.getAllBySpecialization(scanner.next());
                    System.out.println("emri pacientit");
                    String emriPacientit= scanner.next();
                    System.out.println("shenimet tuaja");
                    String shenimet= scanner.next();
                    System.out.println("statusi eshte ");
                    String statusi= scanner.next();
                    System.out.println("orari fillimit eshte ");
                    String input = scanner.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
                    appointment.setNote(shenimet);
                    appointment.setPatientName(emriPacientit);
                    appointment.setStatus(statusi);
                    appointmentRepository.create(appointment);

                    break;


            }



            }catch (Exception e){
                e.getMessage();

            }


        }*/











/*User user=new User();
user.setUserId(2);
user.setUserName("Hendri");
    user.setUserRole("Menaxher");
userRepository.createOrUpdate(user);*/

//  Doctor doctor = new Doctor();
//doctor.setDoctorId(2L);
       /* doctor.setDoctorName("mddd");
        doctor.setSpecialization("neurddo");
        doctor.setUsername("Doktor4");
        doctorRepository.updateDoctor(doctor);*/
// doctorRepository.getById(1);
//  System.out.println(doctorRepository.getById(1));
//     System.out.printf("sucess");

//        doctorRepository.selectAllDoctors();
//        System.out.println("Vendos id e doktorit qe do ndryshosh");
//        Integer id = scanner.nextInt();
//        Doctor doctor = doctorRepository.getById(id);
//        System.out.println("shkruaj usernamin e ri");
//        String username = scanner.next();
//        doctor.setUsername(username);
//        System.out.println("Vendos emrin e ri");
//        String name = scanner.next();
//        doctor.setDoctorName(name);
//        System.out.println("Vendos specializimin e ri");
//        String spec = scanner.next();
//        doctor.setSpecialization(spec);
//        doctorRepository.updateDoctor(doctor);


//  Doctor doctor=new Doctor();
        /*doctor.setDoctorId(6);
        doctor.setDoctorName("doctoddr3");
        doctor.setUsername("s3");
        doctor.setSpecialization("neUROlog");*/


// System.out.println(doctorRepository.getAllBySpecialization("neURO"));








