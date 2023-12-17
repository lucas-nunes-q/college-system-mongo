/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.facom39701.mongoapp1;

/**
 *
 * @author Lucas Nunes Queiroz - 12011GIN005
 */
import model.Course;
import model.Score;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.Arrays;
import java.util.Scanner;
import org.bson.conversions.Bson;

public class MongoApp1 {

    private static MongoClient getClient() {
        final String url = "mongodb://0.0.0.0:27017/?retryWrites=true&w=majority";
        final MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(url))
                        .build();
        return MongoClients.create(settings);
    }

    public static void main(final String[] args) {
        
        try (MongoClient mongoClient = getClient()) {
            final MongoDatabase db = mongoClient.getDatabase("sample_training");
            final CourseRepository courseRepo = new CourseRepository(db);
            final StudentRepository studentRepo = new StudentRepository(db);
            Scanner sc = new Scanner(System.in);
        
            int select = -1;
            do{           
                System.out.println("SchoolCool");
                System.out.println("");
                System.out.println("(1) Cadastrar disciplina");
                System.out.println("(2) Cadastrar aluno");
                System.out.println("(3) Adicionar disciplina a aluno");
                System.out.println("(4) Adicionar nota a aluno");
                System.out.println("(5) Listar notas por disciplina");
                System.out.println("(6) Listar notas por aluno");
                System.out.println("(0) Sair do aplicativo");
                System.out.println("");
                System.out.println("Digite uma das opcoes acima: ");            
                select = Integer.parseInt(sc.nextLine());
            
                Scanner sc1 = new Scanner(System.in);
            
                switch(select){
                    case 1:
                        Course course = new Course();
                        System.out.println("");
                        System.out.println("Digite o codigo da disciplina: ");
                        course.setId(sc1.nextLine());
                        System.out.println("Digite o nome da disciplina: ");
                        course.setName(sc1.nextLine());
                        System.out.println("Digite o nome do professor: ");
                        course.setTeacher(sc1.nextLine());
                        System.out.println("");
                        courseRepo.save(course);
                        System.out.println("Disciplina cadastrada!");  //cadastra uma disciplina no sistema
                        break;
                
                    case 2:
                        Student std = new Student();
                        System.out.println("");
                        System.out.println("Digite a matricula do aluno: ");
                        std.setId(sc1.nextLine());
                        System.out.println("Digite o nome do aluno: ");
                        std.setName(sc1.nextLine());
                        System.out.println("");
                        studentRepo.save(std);
                        System.out.println("Aluno cadastrado!");  //cadastra um aluno no sistema
                        break;
                    
                    case 3:
                        System.out.println("");
                        System.out.println("Alunos disponiveis: ");
                        for (final Student s: studentRepo.list()){
                            System.out.println(s.getId());
                        };
                        System.out.println("");
                        System.out.println("Digite a matricula de um aluno: ");
                        Student std1 = studentRepo.get(sc1.next());
                        System.out.println("");
                        System.out.println("Disciplinas disponiveis: ");
                        for (final Course c: courseRepo.list()){
                            System.out.println(c.getId());
                        };
                        System.out.println("");
                        System.out.println("Digite o codigo da disciplina: ");
                        Course c1 = courseRepo.get(sc1.next());
                        std1.getCourses().add(c1.getId());
                        studentRepo.save(std1);
                        System.out.println("Disciplina " + c1.getId() + " adicionada ao aluno " + std1.getId() + "!");  //adiciona disciplinas a um aluno
                        break;
                    
                    case 4:
                        Score sco = new Score();
                        System.out.println("");
                        System.out.println("Alunos disponiveis: ");
                        for (final Student s: studentRepo.list()){
                            System.out.println(s.getId());
                        };
                        System.out.println("");
                        System.out.println("Digite a matricula de um aluno: ");
                        Student std2 = studentRepo.get(sc1.next());
                        System.out.println("");
                        System.out.println("Disciplinas disponiveis: ");
                        System.out.println(std2.getCourses());
                        System.out.println("");
                        System.out.println("Digite o codigo da disciplina: ");
                        sco.setCourseId(sc1.nextLine());
                        System.out.println("");
                        System.out.println("Digite a origem da nota: ");
                        sco.setType(sc1.nextLine());
                        System.out.println("Digite o valor da nota: ");
                        sco.setScore(sc1.nextDouble());
                        System.out.println("");
                        std2.getScores().add(sco);
                        std2.setScores(std2.getScores());
                        studentRepo.save(std2);
                        System.out.println("Nota para a disciplina " + sco.getCourseId() + " foi adicionada ao aluno " + std2.getId() + "!");  //adiciona notas a um aluno
                        break;
                    
                    case 5:
                        System.out.println("");
                        System.out.println("Disciplinas disponiveis: ");
                        for (final Course c: courseRepo.list()){
                            System.out.println(c.getId());   //lista disciplinas
                        };
                        System.out.println("");
                        System.out.println("Digite o codigo de uma disciplina: ");
                        Course c2 = courseRepo.get(sc1.next());
                        for (final Student s2 : studentRepo.list()){
                            if (s2.getCourses().contains(c2.getId())){
                                System.out.println(s2.getId() + ": " + s2.getName());
                                for (final Score sco2 : s2.getScores()){
                                    if (c2.getId().equals(sco2.getCourseId())){
                                        //System.out.println(s2.getId() + ": " + s2.getName());
                                        System.out.println(sco2.getType() + ": " + sco2.getScore());  //mostra as notas por disciplina
                                        
                                    }
                                }
                            }
                        }
                        System.out.println("");
                        
                        break;
                    
                    case 6:
                        System.out.println("");
                        System.out.println("Alunos disponiveis: ");
                        for (final Student s1: studentRepo.list()){
                            System.out.println(s1.getId());      //lista alunos
                        };
                        System.out.println("");
                        System.out.println("Digite a matricula de um aluno: ");
                        Student std3 = studentRepo.get(sc1.next());
                        System.out.println("");
                        System.out.println("Notas: ");
                        for (final Score sco1 : std3.getScores()){
                            System.out.println(sco1.getCourseId());
                            System.out.println(sco1.getType() + ": " + sco1.getScore());
                            System.out.println("");                 //mostra as notas do aluno           
                        }
                        break;
                    
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
                
            }while(select != 0);
            
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
