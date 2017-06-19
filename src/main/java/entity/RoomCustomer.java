package entity;

import javax.persistence.*;

/**
 * Created by marioquer on 2017/3/15.
 */
@Entity
@Table(name = "room_customer", schema = "hoome", catalog = "")
public class RoomCustomer {
    private long id;
    private long recordId;
    private String name;
    private byte gender;
    private String identityId;
    private BookRecord bookRecordByRecordId;

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
    @Column(name = "record_id", nullable = false)
    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "identity_id", nullable = false, length = 20)
    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

}
