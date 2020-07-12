package com.dicoding.mysharedpreferences;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    String name;
    String email;
    int age;
    String phoneNumber;
    boolean isLove;

    public UserModel(){}

    protected UserModel(Parcel in) {
        name = in.readString();
        email = in.readString();
        age = in.readInt();
        phoneNumber = in.readString();
        isLove = in.readByte() != 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLove(boolean love) {
        isLove = love;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getAge() {
        return age;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public boolean isLove() {
        return isLove;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeInt(age);
        dest.writeString(phoneNumber);
        dest.writeByte((byte) (isLove ? 1 : 0));
    }
}
