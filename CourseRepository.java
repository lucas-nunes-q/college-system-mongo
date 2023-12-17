/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author docker
 */
import model.Course;
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

public class CourseRepository {

    private static Course documentToCourse(final Document doc) {
        final Course course = new Course();
        course.setMongoId(doc.getObjectId("_id"));
        course.setId(doc.getString("id"));
        course.setName(doc.getString("name"));
        course.setTeacher(doc.getString("teacher"));
        return course;
    }
    private final MongoCollection< Document> collection;

    public CourseRepository(final MongoDatabase db) {
        this.collection = db.getCollection("courses");
    }

    public Course get(final String courseId) {
        final Bson filter = Filters.eq("id", courseId);
        final Document doc = collection.find(filter).first();
        return documentToCourse(doc);
    }

    public void save(final Course course) {
        final Bson filter = Filters.eq("id", course.getId());
        final List<Bson> updates = new ArrayList<>();
        updates.add(Updates.set("id", course.getId()));
        updates.add(Updates.set("name", course.getName()));
        updates.add(Updates.set("teacher", course.getTeacher()));
        final Bson document = Updates.combine(updates);
        final UpdateOptions opts = new UpdateOptions().upsert(true);
        collection.updateOne(filter, document, opts);
    }

    public MongoIterable<Course> list() {
        return list(Filters.empty());
    }

    public MongoIterable<Course> list(final Bson filter) {
        return collection.find(filter).map(new Function< Document, Course>() {
            @Override
            public Course apply(final Document doc) {
                return documentToCourse(doc);
            }
        });
    }
}
