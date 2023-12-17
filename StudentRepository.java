/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.Score;
import model.Student;
import com.mongodb.Function;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author docker
 */
public class StudentRepository {

    private static Score documentToScore(final Document doc) {
        final Score score = new Score();
        score.setCourseId(doc.getString("courseId"));
        score.setType(doc.getString("type"));
        score.setScore(doc.getDouble("score"));
        return score;
    }

    private static Student documentToStudent(final Document doc) {
        final Student student = new Student();
        student.setMongoId(doc.getObjectId("_id"));
        student.setId(doc.getString("id"));
        student.setName(doc.getString("name"));
        final List< String> courses = doc.getList("courses", String.class);
        if (courses != null) {
            student.setCourses(courses);
        }
        final List< Document> scoresDoc = doc.getList("scores", Document.class);
        if (scoresDoc != null) {
            for (final Document scoreDoc : scoresDoc) {
                student.getScores().add(documentToScore(scoreDoc));
            }
        }
        return student;
    }
    private final MongoCollection< Document> collection;

    public StudentRepository(final MongoDatabase db) {
        this.collection = db.getCollection("students");
    }

    public Student get(final String studentId) {
        final Bson filter = Filters.eq("id", studentId);
        final Document doc = collection.find(filter).first();
        return documentToStudent(doc);
    }

    public void save(final Student student) {
        final Bson filter = Filters.eq("id", student.getId());
        final List<Bson> updates = new ArrayList<>();
        updates.add(Updates.set("id", student.getId()));
        updates.add(Updates.set("name", student.getName()));
        updates.add(Updates.set("courses", student.getCourses()));
        final List< Document> scoresDoc = new ArrayList<>();
        for (final Score score : student.getScores()) {
            final Document scoreDoc
                    = new Document("courseId", score.getCourseId())
                            .append("type", score.getType())
                            .append("score", score.getScore());
            scoresDoc.add(scoreDoc);
        }
        updates.add(Updates.set("scores", scoresDoc));
        final Bson document = Updates.combine(updates);
        final UpdateOptions opts = new UpdateOptions().upsert(true);
        collection.updateOne(filter, document, opts);
    }

    public MongoIterable<Student> list() {
        return list(Filters.empty());
    }

    public MongoIterable<Student> list(final Bson filter) {
        return collection.find(filter).map(new Function<Document, Student>() {
            @Override
            public Student apply(final Document doc) {
                return documentToStudent(doc);
            }
        });
    }
}
