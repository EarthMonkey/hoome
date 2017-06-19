package dao;

import entity.BookRecord;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface BookRecordDao {

    boolean addRecord(BookRecord bookRecord);

    boolean updateRecord(BookRecord bookRecord);

    List<BookRecord> getRecordByUser(Integer id);

    BookRecord getRecord(Long id);

    List<BookRecord> getRecordByHoterOwner(Integer id);

    Long maxId();

    List<BookRecord> getRecords();
}
