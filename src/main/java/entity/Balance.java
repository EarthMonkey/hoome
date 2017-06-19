package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by marioquer on 2017/3/15.
 */
@Entity
public class Balance {
    private int id;
    private int vipId;
    private byte type;
    private double money;
    private double balance;
    private Timestamp time;
    private VipCard vipCardByVipId;

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
    @Column(name = "vip_id", nullable = false)
    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance1 = (Balance) o;

        if (id != balance1.id) return false;
        if (vipId != balance1.vipId) return false;
        if (type != balance1.type) return false;
        if (Double.compare(balance1.money, money) != 0) return false;
        if (Double.compare(balance1.balance, balance) != 0) return false;
        if (time != null ? !time.equals(balance1.time) : balance1.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + vipId;
        result = 31 * result + (int) type;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "vip_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false
    )
    public VipCard getVipCardByVipId() {
        return vipCardByVipId;
    }

    public void setVipCardByVipId(VipCard vipCardByVipId) {
        this.vipCardByVipId = vipCardByVipId;
    }
}
