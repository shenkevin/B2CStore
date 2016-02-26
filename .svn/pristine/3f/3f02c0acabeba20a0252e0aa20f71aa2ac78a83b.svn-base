package cn.mstar.store.db.entityToSave;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Administrator on 2015/8/12.
 */

@Table(name = "ProAndSpecialIdz")
public class ProAndSpecialIdz extends Model{


    @Column (name = "proId")
    public int proId;


    @Column (name = "specialId")
    public int specialId;

    @Column (name = "itemCount")
    public int number;

    public ProAndSpecialIdz(int proId, int specialId, int number) {
        super();
        this.proId = proId;
        this.specialId = specialId;
        this.number = number;
    }

    public ProAndSpecialIdz() {
        super();
    }

    @Override
    public String toString() {
        return "ProAndSpecialIdz{" +
                "proId=" + proId +
                ", specialId=" + specialId +
                ", number=" + number +
                '}';
    }

    public static List<ProAndSpecialIdz> getAll() {
        return new Select()
                .from(ProAndSpecialIdz.class)
                .execute();
    }

    public static void cleanLocal() {

    }
}
