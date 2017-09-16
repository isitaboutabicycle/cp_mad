package course.examples.DataManagement.DataBaseExample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	final static String TABLE_NAME = "authors";
	final static String AUTHOR_NAME = "name";
	final static String _ID = "_id";
	final static String[] columns = { _ID, AUTHOR_NAME };

	final private static String CREATE_CMD =

	"CREATE TABLE authors (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ AUTHOR_NAME + " TEXT NOT NULL)";

	final private static String NAME = "author_db";
	final private static Integer VERSION = 1;
	final private Context mContext;

	public DatabaseOpenHelper(Context context) {
		super(context, NAME, null, VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}

	void deleteDatabase() {
		mContext.deleteDatabase(NAME);
	}
}
