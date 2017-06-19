package entity;

import javax.persistence.*;

/**
 * Created by marioquer on 2017/3/15.
 */
@Entity
@Table(name = "bank_card", schema = "hoome", catalog = "")
public class BankCard {
    private String id;
    private int userId;
    private String bank;
    private User userByUserId;

    @Id
    @Column(name = "id", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
