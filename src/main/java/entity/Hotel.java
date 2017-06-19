package entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by marioquer on 2017/3/15.
 */
@Entity
public class Hotel {
    private int id;
    private int smallNum;
    private int bigNum;
    private String address;
    private String phone;
    private int ownerId;
    private String introduction;
    private Collection<BookRecord> bookRecordsById;
    private User userByOwnerId;
    private Collection<Room> roomsById;
    private String name;

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
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "small_num", nullable = false)
    public int getSmallNum() {
        return smallNum;
    }


    public void setSmallNum(int smallNum) {
        this.smallNum = smallNum;
    }

    @Basic
    @Column(name = "big_num", nullable = false)
    public int getBigNum() {
        return bigNum;
    }

    public void setBigNum(int bigNum) {
        this.bigNum = bigNum;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "owner_id", nullable = false)
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "introduction", nullable = true, length = 255)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
