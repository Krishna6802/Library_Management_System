package com.example.booktrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "book_track.db";
    public DBHelper(Context context) {
        super(context, "book_track.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_user (u_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "u_full_name TEXT NOT NULL," +
                "u_name TEXT NOT NULL," +
                "u_pass TEXT NOT NULL," +
                "u_email TEXT NOT NULL," +
                "u_contact TEXT NOT NULL," +
                "u_address TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_cat (c_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "c_name TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_book (b_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "b_cat_id INTEGER NOT NULL," +
                "b_title TEXT NOT NULL," +
                "b_author TEXT NOT NULL," +
                "b_lang TEXT NOT NULL," +
                "b_publication TEXT NOT NULL," +
                "b_edition TEXT NOT NULL," +
                "b_pages TEXT NOT NULL," +
                "b_status TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_book_req (r_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "r_u_name TEXT NOT NULL,"+
                "r_b_id INTEGER NOT NULL,"+
                "r_status INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_book_issue (bi_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "u_name TEXT NOT NULL,"+
                "b_id INTEGER NOT NULL,"+
                "bi_date DATE NOT NULL,"+
                "br_date DATE NOT NULL,"+
                "bi_status Integer NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tbl_user");
        db.execSQL("DROP TABLE IF EXISTS tbl_admin");
    }

    public boolean insertUserData(String fullName, String userName, String password, String email, String contactNo, String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("u_full_name",fullName);
        values.put("u_name",userName);
        values.put("u_pass",password);
        values.put("u_email",email);
        values.put("u_contact",contactNo);
        values.put("u_address",address);

        long result = db.insert("tbl_user",null,values);
        if(result==-1) return false;
        else return true;
    }
    public boolean chkUserName(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_user WHERE u_name=?",new String[] {userName});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean chkContactNo(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tbl_user WHERE u_contact=?",new String[] {contactNo});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean chkMember(String userName, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_user WHERE u_name=? and u_pass=?",new String[] {userName, password});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Cursor getProfile(String uname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_user where u_name=?",new String[] {uname});
        return cursor;
    }

// *********************************************** Admin Panel ***************************************************************

    //--------------------------------------------------------- tbl_cat ------------------------------------------------------------

    public void createCat() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_cat (c_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "c_name TEXT NOT NULL)");
    }

    public boolean insertCatData(String catName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("c_name",catName);

        long result = db.insert("tbl_cat",null,values);
        if(result==-1) return false;
        else return true;
    }
    public boolean chkCat(String CatName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_cat WHERE c_name=?",new String[] {CatName});
        if(cursor.getCount() > 0) return true;
        else return false;
    }
    public Cursor getCatData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_cat", null);
        return cursor;
    }
    public int delCat(String c_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_cat","c_id=?",new String[] {c_id});
    }
    public boolean editCat(String c_id,String n_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("c_name",n_name);
        long r = db.update("tbl_cat",c,"c_id=?",  new String[] {c_id});
        if(r==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    //--------------------------------------------------------- tbl_book ------------------------------------------------------------
    public void createBook() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_book (b_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "b_cat_id INTEGER NOT NULL," +
                "b_title TEXT NOT NULL," +
                "b_author TEXT NOT NULL," +
                "b_lang TEXT NOT NULL," +
                "b_publication TEXT NOT NULL," +
                "b_edition TEXT NOT NULL," +
                "b_pages TEXT NOT NULL," +
                "b_status TEXT NOT NULL)");
    }

    public boolean insertBookData(Integer Cat_id, String bookTitle, String bookAuthor, String bookLang, String bookPublication, String bookEdition, String bookPages)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("b_cat_id",Cat_id);
        values.put("b_title",bookTitle);
        values.put("b_author",bookAuthor);
        values.put("b_lang",bookLang);
        values.put("b_publication",bookPublication);
        values.put("b_edition",bookEdition);
        values.put("b_pages",bookPages);
        values.put("b_status","1");

        long result = db.insert("tbl_book",null,values);
        if(result==-1) return false;
        else return true;
    }

    public boolean chkBook(String BookTitle, String AuthorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_book WHERE b_title=? and b_author=?",new String[] {BookTitle,AuthorName});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Cursor getBookData(String Cat_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_book where b_cat_id=?", new String[] {Cat_id});
        return cursor;
    }

    public Cursor getBookDetails(String b_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_book where b_id=?", new String[] {b_id});
        return cursor;
    }

    public int delBook(String b_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_book","b_id=?",new String[] {b_id});
    }
    public boolean editBook(String b_id,String n_title, String n_author, String n_lang, String n_publication, String n_edition, String n_pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("b_title",n_title);
        c.put("b_author",n_author);
        c.put("b_lang",n_lang);
        c.put("b_publication",n_publication);
        c.put("b_edition",n_edition);
        c.put("b_pages",n_pages);
        long r = db.update("tbl_book",c,"b_id=?",  new String[] {b_id});
        if(r==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    public boolean editBookIssueStatus(String b_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues b = new ContentValues();
        b.put("b_status", 0);
        long r = db.update("tbl_book", b, "b_id=?", new String[]{b_id});
        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean chkBookStatus(String b_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_book WHERE b_id=? and b_status=0",new String[] {b_id});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    /*public Cursor getBookReq()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_book where b_status=0", null);
        return cursor;
    }*/

    //------------------------------------------------------ tbl_book_req ----------------------------------------------------

    public void createBookReq() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_book_req (r_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "r_u_name TEXT NOT NULL,"+
                "r_b_id INTEGER NOT NULL,"+
                "r_status INTEGER NOT NULL)");
    }

    public boolean insertBookReq(String u_name, String b_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("r_u_name",u_name);
        values.put("r_b_id",b_id);
        values.put("r_status","0");

        long result = db.insert("tbl_book_req",null,values);
        if(result==-1) return false;
        else return true;
    }

    public Cursor disBookRequests()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT r.r_id, r.r_u_name, r.r_b_id, b.b_title FROM tbl_book_req r inner join tbl_book b on r.r_b_id = b.b_id",null);
        return cursor;
    }
 //   -------------------------------------------------------------------------------------------------------------------------------

    public void createBookIssue()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_book_issue (bi_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "u_name TEXT NOT NULL,"+
                "b_id INTEGER NOT NULL,"+
                "bi_date DATE NOT NULL,"+
                "br_date DATE NOT NULL,"+
                "bi_status Integer NOT NULL)");
    }

    public boolean insertBookIssue(String u_name, String b_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Date curr_date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

        String formattedDate = dateFormat.format(curr_date);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 15);
        Date dateAfter15Days = calendar.getTime();

        String returnDate = dateFormat.format(dateAfter15Days);

        values.put("u_name",u_name);
        values.put("b_id",b_id);
        values.put("bi_date", formattedDate);
        values.put("br_date",returnDate);
        values.put("bi_status","0");

        long result = db.insert("tbl_book_issue",null,values);
        if(result==-1) return false;
        else return true;
    }


}
