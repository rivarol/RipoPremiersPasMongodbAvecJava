package principal;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MyFirstMongodb {

	public static void main(String[] args) {
		try {
			//etablir une connexion au serveur
			MongoClient mClient=new MongoClient("localhost", 27017);
		
			//Etablier une connexion à une base de données
			DB mDb=mClient.getDB("appMongoDb");
		
			//Afficher toutes les bases de données
			System.out.println("Afficher toutes les bases de données");
			mClient.getDatabaseNames().forEach(System.out::println);
			
			//Créer une collection
			mDb.createCollection("persons", null);
			
			//Afficher toutes les collections de la bd nouvelllement créée
			System.out.println("\nAfficher toutes les collections de la base des données appMongoDb");
			mDb.getCollectionNames().forEach(System.out::println);
			
			//CRUD
			//Insert
			System.out.println("\nCRUD");
			System.out.println("Insert");
			DBCollection cP=mDb.getCollection("persons");
			//1 er document
			BasicDBObject docuement=new BasicDBObject();
			docuement.put("name", "Jean");
			cP.insert(docuement);
			//2eme document
			BasicDBObject docuement2=new BasicDBObject();
			docuement2.put("name", "Sam");
			cP.insert(docuement2);
			//3eme document
			BasicDBObject docuement3=new BasicDBObject();
			docuement3.put("name", "Luc");
			cP.insert(docuement3);
			
			//Lecture de tous les documents
			System.out.println("-------");
			DBCursor dBCursor=cP.find();
			while(dBCursor.hasNext()) {
				System.out.println(dBCursor.next());
			}
			
			//Update
			BasicDBObject update=new BasicDBObject();
			update.put("name" ,"Matthieu");
			
			BasicDBObject query=new BasicDBObject().append("name", "Jean");
			cP.update(query, update);
			
			
			//Lecture dU document mis à jour
			System.out.println("\n");
			BasicDBObject findD=new BasicDBObject();
			findD.put("name", "Matthieu");
			
			dBCursor=cP.find(findD);
			while(dBCursor.hasNext()) {
				System.out.println(dBCursor.next());
			}
			
			//Delete
			cP.remove(findD);
			
			//Lecture des documents restant
			System.out.println("\n");
			
			dBCursor=cP.find();
			while(dBCursor.hasNext()) {
				System.out.println(dBCursor.next());
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
