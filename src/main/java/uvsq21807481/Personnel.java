package uvsq21807481;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Personnel extends Equipe implements Serializable{

    private final int ID;
    private final String lastName;
    private final String firstName;
    private final String job;
    private final LocalDate birth;
    private final List<String> phone;

    public static class Builder{
        private final int ID;
        private final String lastName;
        private final String firstName;
        private final String job;
        private final LocalDate birth;
        private final List<String> phone;

        public Builder(int ID, String lastName, String firstName, String date, String job){
            this.ID = ID;
            this.lastName = lastName;
            this.firstName = firstName;
            this.birth = LocalDate.parse(date);
            this.job = job;
            this.phone = new ArrayList<>();
        }

        public Builder addPhone(String number){
            phone.add(number);
            return this;
        }

        public Personnel build(){
            return new Personnel(this);
        }
    }

    public Personnel(Builder b){
        this.ID = b.ID;
        this.lastName = b.lastName;
        this.firstName = b.firstName;
        this.birth = b.birth;
        this.job = b.job;
        this.phone = b.phone;
    }

    public int getID() { return ID; }

    public String getLastName(){
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirth() { return birth; }

    public String getJob() {
        return job;
    }

    public List<String> getPhone() {
        return phone;
    }
}
