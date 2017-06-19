package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by marioquer on 2017/3/15.
 */
@Entity
@Table(name = "room", schema = "hoome", catalog = "")
public class Room {
    private int id;
    private int hotelId;
    private byte roomStyle;
    private double price;
    private Double specialPrice;
    private Timestamp specialTime;
    private String hotelName;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "special_price", nullable = true, precision = 0)
    public Double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Basic
    @Column(name = "special_time", nullable = true)
    public Timestamp getSpecialTime() {
        return specialTime;
    }

    public void setSpecialTime(Timestamp specialTime) {
        this.specialTime = specialTime;
    }

    @Basic
    @Column(name = "hotel_name", nullable = true)
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }


}
