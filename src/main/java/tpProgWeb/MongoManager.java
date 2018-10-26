package tpProgWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

class MongoManager {

    private static MongoManager instance = new MongoManager();

    private MongoCollection<Document> userCollection;

    private MongoManager() {
        MongoClient client = new MongoClient();
        String DB_NAME = "username";
        MongoDatabase database = client.getDatabase(DB_NAME);
        String USER_COLLECTION = "users";
        userCollection = database.getCollection(USER_COLLECTION);
    }

    static MongoManager getInstance(){
        return instance;
    }

    boolean addUser(User u){
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

    FindIterable<Document> getAll(){
        return userCollection.find();
    }

    boolean deleteUser(String id){
        return (userCollection.deleteOne(new Document("_id", new ObjectId(id))).getDeletedCount() == 1);
    }

    void setUser(String id, String firstName, String lastName){
        BasicDBObject objectId = new BasicDBObject("_id", new ObjectId(id));
        if (!firstName.equals(""))
            userCollection.updateOne(new BasicDBObject(objectId), new BasicDBObject("$set", new BasicDBObject("FirstName", firstName)));
        if (!lastName.equals(""))
            userCollection.updateOne(new BasicDBObject(objectId), new BasicDBObject("$set", new BasicDBObject("LastName", lastName)));
    }


}
