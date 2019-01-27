package com.igor.scrumassistant.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

@Entity
public class Executor {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String surname;
    private Role role;

    public Executor(String name, String surname, Role role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public Executor() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;

        Executor executor = (Executor) obj;
        return (executor.getName().equals(this.name) &&
                executor.getSurname().equals(this.surname) &&
                executor.getRole().equals(this.role));
    }

    @Override
    public int hashCode() {
        return (name.hashCode() +
                surname.hashCode() +
                role.hashCode());
    }
}
