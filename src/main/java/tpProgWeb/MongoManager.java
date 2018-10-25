package tpProgWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.io.IOException;
import java.io.StringWriter;

public class MongoManager {

    private static MongoManager instance = new MongoManager();

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> userCollection;

    private String DB_NAME = "username";
    private String USER_COLLECTION = "users";

    private MongoManager() {
        client = new MongoClient();
        database = client.getDatabase(DB_NAME);
        userCollection = database.getCollection(USER_COLLECTION);
    }

    public static MongoManager getInstance(){
        return instance;
    }

    public boolean addUser(User u){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(u);
            Document doc = Document.parse(jsonString);
            userCollection.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public FindIterable<Document> getAll(){
        return userCollection.find();
    }

    public boolean deleteUser(String id){
        return userCollection.deleteOne(new Document("_id", new ObjectId(id))).getDeletedCount() == 1;
    }

    public void setUser(String id, String firstName, String lastName){
        BasicDBObject objectId = new BasicDBObject("_id", new ObjectId(id));
        if (!firstName.equals(""))
            userCollection.updateOne(new BasicDBObject(objectId), new BasicDBObject("$set", new BasicDBObject("FirstName", firstName)));
        if (!lastName.equals(""))
            userCollection.updateOne(new BasicDBObject(objectId), new BasicDBObject("$set", new BasicDBObject("LastName", lastName)));
    }


}
