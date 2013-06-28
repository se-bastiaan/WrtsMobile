package nl.digischool.wrts.database;

import nl.digischool.wrts.classes.Params;
import android.content.Context;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import nl.digischool.wrts.classes.Utilities;

public class DbHelper {

	private ObjectContainer database;
	private static final int DATABASE_MODE = 0;
	private Context context; 

	public DbHelper(Context context) {
		this.context = context;
		this.database = null;
	}

	public void openDatabase() {
		try {
			if (this.database == null) {
				this.database = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), getDbPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeDatabase() {
		if (this.database != null) {
			this.database.close();
		}
	}

	private String getDbPath() {
		return context.getDir("data", DATABASE_MODE) + "/" + Params.databaseName;
	}

	public ObjectContainer openDbSession() {
		try {
			return database.ext().openSession();
		} catch (Exception e) {
			Utilities.log("DbHelper", "Check if database is opened");
			e.printStackTrace();
		}
		return null;
	}

}