package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by marioquer on 2017/3/15.
 */
@Entity
@Table(name = "book_record", schema = "hoome", catalog = "")
public class BookRecord {
    private long id;
    private int bookerId;
    private double discount;
    private double amount;
    private int hotelId;
    private byte roomStyle;
    private byte status;
    private Timestamp bookTime;
    private Timestamp inTime;
    private Timestamp outTime;
    private Byte payMethod;
    private String targetInTime;
    private String targetOutTime;
    private String hotelName;
    private Byte isPaid;

    @Basic
    @Column(name = "isPaid", nullable = false)
    public byte getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(byte isPaid) {
        this.isPaid = isPaid;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "booker_id", nullable = false)
    public int getBookerId() {
        return bookerId;
    }

    public void setBookerId(int bookerId) {
        this.bookerId = bookerId;
    }

    @Basic
    @Column(name = "discount", nullable = false, precision = 0)
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "hotel_id", nullable = false)
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Basic
    @Column(name = "room_style", nullable = false)
    public byte getRoomStyle() {
        return roomStyle;
    }

    public void setRoomStyle(byte roomStyle) {
        this.roomStyle = roomStyle;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "book_time", nullable = false)
    public Timestamp getBookTime() {
        return bookTime;
    }

    public void setBookTime(Timestamp bookTime) {
        this.bookTime = bookTime;
    }

    @Basic
    @Column(name = "in_time", nullable = true)
    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    @Basic
    @Column(name = "out_time", nullable = true)
    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    @Basic
    @Column(name = "pay_method", nullable = true)
    public Byte getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Byte payMethod) {
        this.payMethod = payMethod;
    }

    @Basic
    @Column(name = "target_in_time", nullable = false, length = 60)
    public String getTargetInTime() {
        return targetInTime;
    }

    public void setTargetInTime(String targetInTime) {
        this.targetInTime = targetInTime;
    }

    @Basic
    @Column(name = "target_out_time", nullable = true, length = 60)
    public String getTargetOutTime() {
        return targetOutTime;
    }

    public void setTargetOutTime(String targetOutTime) {
        this.targetOutTime = targetOutTime;
    }

    @Basic
    @Column(name = "hotel_name", nullable = true, length = 20)
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }


}
